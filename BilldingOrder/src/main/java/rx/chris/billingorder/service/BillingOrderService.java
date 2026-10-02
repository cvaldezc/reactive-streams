package rx.chris.billingorder.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.billingorder.mapper.OrderMapper;
import rx.chris.billingorder.model.dto.BalanceResponse;
import rx.chris.billingorder.model.dto.OrderRequest;
import rx.chris.billingorder.model.Order;
import rx.chris.billingorder.repository.OrderEntity;
import rx.chris.billingorder.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Service
public class BillingOrderService {

    private final WebClient webClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public Mono<Order> billingOrder(OrderRequest request) {
        return getBalance(request.userId())
                .flatMap(balance -> {
                    if (balance.amount().compareTo(BigDecimal.ZERO) < 0) {
                        return Mono.error(() -> new IllegalArgumentException("Balance cannot be negative"));
                    } else {
                        var unsaved = new OrderEntity();
                        unsaved.setUserId(request.userId());
                        unsaved.setProduct(request.product());
                        unsaved.setPrice(request.price());
                        unsaved.setStatus("COMPLETED");
                        unsaved.setObservation("Balance " + balance.amount().setScale(2, BigDecimal.ROUND_HALF_UP));
                        return orderRepository.save(unsaved);
                    }
                })
                .map(orderMapper::toMapper)
                .doOnError(throwable -> log.error("Fallo general al procesar la ordern", throwable));

    }

    private Mono<BalanceResponse> getBalance(String userId) {
        return webClient.get()
                .uri("/api/balance/{user}", userId)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve()
                .bodyToMono(BalanceResponse.class)
                .timeout(Duration.ofSeconds(2))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreakerRegistry.circuitBreaker("legacyBalance")))
                .onErrorResume(throwable -> fallbackBalance(userId, throwable));
    }

    private Mono<BalanceResponse> fallbackBalance(String userId, Throwable throwable) {
        log.info("Falling back to legacy balance, apply courtesy balance of 50 USD" );
        return Mono.just(new BalanceResponse(userId, BigDecimal.valueOf(50)));
    }

}

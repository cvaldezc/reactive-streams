package rx.chris.ordersmanager.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.ordersmanager.exception.CustomBusinessException;
import rx.chris.ordersmanager.model.Order;
import rx.chris.ordersmanager.model.OrderRequest;
import rx.chris.ordersmanager.model.ReserveRequest;
import rx.chris.ordersmanager.model.ReserveResponse;
import rx.chris.ordersmanager.repository.OrderEntity;
import rx.chris.ordersmanager.repository.OrderRepository;

import java.time.Duration;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    private final CircuitBreakerRegistry registry;

    public Mono<Order> createOrder(OrderRequest request) {
        return Mono.fromCallable(() -> {
                    var unsaved = new OrderEntity();
                    unsaved.setCustomerId(request.customerId());
                    unsaved.setProductId(request.productId());
                    unsaved.setQuantity(request.quantity());
                    unsaved.setStatus("PENDING");
                    return unsaved;
                }).flatMap(orderRepository::save)
                .map(order -> new Order(order.getId(), order.getCustomerId(), order.getProductId(), order.getQuantity(), order.getStatus()));
    }

    public Mono<Order> updateOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new CustomBusinessException("Orden no encontrada")))
                .zipWhen(order -> hasReserved(new ReserveRequest(order.getProductId(),  order.getQuantity())))
                .flatMap(tuple -> {
                    if(Objects.nonNull(tuple.getT2().hasfallback()) && tuple.getT2().hasfallback()) {
                        var order =  tuple.getT1();
                        order.setStatus("MANUAL_REVIEW");
                        return orderRepository.save(order);
                    }
                    if (tuple.getT2().reserved()) {
                        var order =  tuple.getT1();
                        order.setStatus("COMPLETED");
                        return orderRepository.save(order);
                    }
                    else {
                        return Mono.error(new CustomBusinessException("Sin stock"));
                    }
                })
                .map(order -> new Order(order.getId(), order.getCustomerId(), order.getProductId(), order.getQuantity(), order.getStatus()));
    }

    private Mono<ReserveResponse> hasReserved(ReserveRequest request) {
        return webClient.post()
                .uri(uriBuilder ->
                        uriBuilder.path("/api/inventory/reserve").build())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ReserveResponse.class)
                .timeout(Duration.ofSeconds(5))
                .transformDeferred(CircuitBreakerOperator.of(registry.circuitBreaker("reserve")))
                .onErrorResume(t -> fallbackClient())
                .doOnNext(t -> log.info("Reserved {}", t));
    }

    private Mono<ReserveResponse> fallbackClient() {
        log.error("Falling back to fallback Reserved");
        return Mono.just(new ReserveResponse(null, Boolean.TRUE));
    }
}

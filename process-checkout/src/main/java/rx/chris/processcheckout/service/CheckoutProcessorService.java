package rx.chris.processcheckout.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.processcheckout.exception.UserOrInventoryException;
import rx.chris.processcheckout.model.CheckoutRequest;
import rx.chris.processcheckout.model.CheckoutResponse;
import rx.chris.processcheckout.model.Inventory;
import rx.chris.processcheckout.model.User;
import rx.chris.processcheckout.repository.CheckoutEntity;
import rx.chris.processcheckout.repository.CheckoutMapper;
import rx.chris.processcheckout.repository.CheckoutRepository;

import java.net.URI;
import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutProcessorService {

    private final WebClient webClient;
    private final CheckoutRepository checkoutRepository;
    private final CheckoutMapper checkoutMapper;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    private static final String BLOCKED_USER = "BLOCKED";

    @Value("${app.invetory.url}")
    private String inventoryUrl;
    @Value("${app.user-legacy.url}")
    private String userUrl;


    public Mono<CheckoutResponse> checkout(CheckoutRequest request) {
        return Mono.zip(getAvailableProduct(request.productId()), getUserLegacy(request.userId()))
                .filter(p -> p.getT1().available() && !p.getT2().status().equals(BLOCKED_USER))
                .switchIfEmpty(Mono.error(() -> new UserOrInventoryException("No cumple con los requisitos")))
                .flatMap(tuple -> {
                    var unsaved = new CheckoutEntity();
                    unsaved.setUserId(request.userId());
                    unsaved.setProductId(request.productId());
                    unsaved.setAmountPaid(tuple.getT1().price());
                    return checkoutRepository.save(unsaved);
                })
                .map(checkoutMapper::toResponse)
                .doOnNext(entity -> log.info("Se persistio la entidad {}", entity));
    }

    private Mono<Inventory> getAvailableProduct(String productId) {
        return webClient
                .get()
                .uri(URI.create(inventoryUrl + "/api/v1/inventory/" + productId))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Inventory.class)
                .doOnNext(entity -> log.info("Inventory {}", entity));

    }

    private Mono<User> getUserLegacy(String userId) {
        return webClient
                .get()
                .uri(URI.create(userUrl + "/api/users/" + userId + "/status"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(User.class)
                .timeout(Duration.ofSeconds(2))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreakerRegistry.circuitBreaker("userlegacy")))
                .onErrorResume(t -> fallbackUser(userId))
                .doOnNext(entity -> log.info("doOnNext User {}", entity));
    }

    private Mono<User> fallbackUser(String userId) {
        log.error("Falling back to fallback user {}", userId);
        return Mono.just(new User("GUEST"));
    }

}

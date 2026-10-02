package rx.chris.orderrequest.infra.adatper.http;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.orderrequest.applicetion.port.out.BalancePort;
import rx.chris.orderrequest.infra.adatper.http.dto.Balance;

import java.math.BigDecimal;
import java.time.Duration;

@Slf4j
@Component
public class LegacyBalanceAdapter implements BalancePort {

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;

    public LegacyBalanceAdapter(WebClient webClient, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.webClient = webClient;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("myLegacy");
    }

    @Override
    public Mono<BigDecimal> getBalance(String userId) {
        return webClient.get()
                .uri("/api/balance/{userid}", userId)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve()
                .bodyToMono(Balance.class)
                 .timeout(Duration.ofSeconds(2))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(e -> fallbackBalance(userId, e))
                .map(Balance::amount)
                .doOnNext(balance -> log.info("get amount from balance {}", balance));
    }

    private Mono<Balance> fallbackBalance(String userId, Throwable ex) {
        log.error("fallback balance {}", userId, ex);
        return Mono.just(new Balance(userId, BigDecimal.valueOf(50)));
    }
}

package rx.chris.servicepayments.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.chris.servicepayments.exceptions.AnalyzeFraudException;
import rx.chris.servicepayments.model.Payment;
import rx.chris.servicepayments.model.dto.AnalyzeResponse;
import rx.chris.servicepayments.model.dto.PaymentRequest;
import rx.chris.servicepayments.model.dto.PaymentResponse;
import rx.chris.servicepayments.repository.PaymentEntity;
import rx.chris.servicepayments.repository.PaymentRepository;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final WebClient webClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public Mono<PaymentResponse> getPayment(PaymentRequest request) {
        return getAnalyzeFraud(request.accountId())
                .flatMap(p -> {
                    if (p.decision().equals("REJECTED") ) {
                        return Mono.error(() ->  new AnalyzeFraudException("Service antifraud to REJECTED payment"));
                    } else {
                    var unsaved = new PaymentEntity();
                    unsaved.setAccountId(request.accountId());
                    unsaved.setAmount(request.amount());
                    unsaved.setStatus(p.decision());
                    return paymentRepository.save(unsaved);
                    }
                 })
                .map(entity -> new PaymentResponse(entity.getId(), entity.getStatus()))
                .doOnError(throwable -> log.error("Error general in the payment process: {}", throwable.getMessage()));
    }

    public Mono<AnalyzeResponse> getAnalyzeFraud(String accountId) {
        return webClient.get()
                .uri("http://localhost:9082/api/v1/analyze/{accountId}", "234")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve()
                .bodyToFlux(AnalyzeResponse.class)
                .transformDeferred(CircuitBreakerOperator.of(circuitBreakerRegistry.circuitBreaker("antifraud")))
                .timeout(Duration.ofSeconds(2))
                .onErrorResume(e -> fallbackFraud(accountId, e))
                .next();
    }

    private Mono<AnalyzeResponse> fallbackFraud(String accountId, Throwable e) {
        log.info("Apply fallback by error antifraud service: {}", e.getMessage());
        return Mono.just(new AnalyzeResponse("MANUAL_REVIEW"));
    }

}

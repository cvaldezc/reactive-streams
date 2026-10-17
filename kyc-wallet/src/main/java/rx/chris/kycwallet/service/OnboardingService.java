package rx.chris.kycwallet.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.kycwallet.exception.CustomException;
import rx.chris.kycwallet.model.Onboarding;
import rx.chris.kycwallet.model.dto.KycResponse;
import rx.chris.kycwallet.model.dto.OnboardingRequest;
import rx.chris.kycwallet.model.dto.WalletRequest;
import rx.chris.kycwallet.model.dto.WalletResponse;
import rx.chris.kycwallet.repository.Customer;
import rx.chris.kycwallet.repository.CustomerRepository;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class OnboardingService {

    private final WebClient kycWebClient;
    private final WebClient walletWebClient;
    private final CustomerRepository repository;
    private final CircuitBreakerRegistry registry;

    public Mono<Onboarding> createOnboarding(OnboardingRequest request) {
        return verifyKyc(request)
                .filter(KycResponse::passed)
                .switchIfEmpty(Mono.error(new CustomException("Rechazado por KYC")))
                .flatMap(kyc -> {
                    var unsaved = new Customer();
                    unsaved.setDocumentId(request.documentId());
                    unsaved.setEmail(request.email());
                    unsaved.setStatus("PENDING");
                    return repository.save(unsaved);
                })
                .zipWhen(cust -> createWallet(new WalletRequest(cust.getId())))
                .flatMap(tuple -> {
                    var res = tuple.getT2();
                    var customer = tuple.getT1();

                    customer.setWalletId(res.walletId());
                    if (res.walletId().equals("ACTIVE_NO_WALLET")) {
                        customer.setStatus(res.status());
                    } else {
                        customer.setStatus("ACTIVE");
                    }
                    return  repository.save(customer);
                })
                .map(o -> new Onboarding(o.getId(), o.getWalletId(), o.getStatus()));
    }


    private Mono<KycResponse> verifyKyc(OnboardingRequest request) {
       return kycWebClient.get()
               .uri(uriBuilder ->
                       uriBuilder.path("/api/kyc/verify/{documentId}").build(request.documentId()))
               .retrieve()
               .bodyToMono(KycResponse.class)
               .doOnNext(kyc -> log.info("kyc response {}", kyc));
    }

    private Mono<WalletResponse> createWallet(WalletRequest request) {
        return walletWebClient.post()
                .uri(uriBuilder ->
                        uriBuilder.path("/api/wallets").build())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WalletResponse.class)
                .timeout(Duration.ofSeconds(2))
                .transformDeferred(CircuitBreakerOperator.of(registry.circuitBreaker("wallet")))
                .onErrorResume(t -> fallbackWallet())
                .doOnNext(wallet -> log.info("wallet response {}", wallet));
    }

    private Mono<WalletResponse> fallbackWallet() {
        log.error("fallback wallet");
        return Mono.just(new WalletResponse("PENDING_WALLET", "ACTIVE_NO_WALLET"));
    }

}

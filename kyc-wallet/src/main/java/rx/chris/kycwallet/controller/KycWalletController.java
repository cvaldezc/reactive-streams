package rx.chris.kycwallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.kycwallet.model.Onboarding;
import rx.chris.kycwallet.model.dto.OnboardingRequest;
import rx.chris.kycwallet.service.OnboardingService;

@RestController
@RequestMapping("/api/v1/onboarding")
@RequiredArgsConstructor
public class KycWalletController {

    private final OnboardingService service;

    @PostMapping
    Mono<Onboarding> onboardingCustomer(@RequestBody OnboardingRequest request) {
        return service.createOnboarding(request);
    }

}

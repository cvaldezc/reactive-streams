package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.KycUseCase;
import rx.chris.thridparty.domain.Kyc;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class VerificationController {

    private final KycUseCase kycUseCase;

    @GetMapping("/verify/{documentId}")
    public Mono<Kyc> getVerify(@PathVariable("documentId") String documentId) {
        return kycUseCase.verify(documentId);
    }

}

package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.KycUseCase;
import rx.chris.thridparty.domain.Kyc;

import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class KycService implements KycUseCase {

    private final Random random = new Random();

    @Override
    public Mono<Kyc> verify(String documentId) {
        return Mono.fromCallable(() -> new Kyc(random.nextBoolean(), random.nextLong(100)))
                .doOnNext(kyc -> log.info("Kyc verified for documentId: {} verify: {}", documentId, kyc));
    }
}

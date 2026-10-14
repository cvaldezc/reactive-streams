package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Kyc;

public interface KycUseCase {

    Mono<Kyc> verify(String documentId);
}

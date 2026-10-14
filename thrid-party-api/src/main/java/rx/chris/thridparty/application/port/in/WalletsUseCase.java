package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Wallet;

public interface WalletsUseCase {

    Mono<Wallet> createWallet(Long customerId);
}

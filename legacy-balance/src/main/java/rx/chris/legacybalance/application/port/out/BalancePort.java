package rx.chris.legacybalance.application.port.out;

import reactor.core.publisher.Mono;
import rx.chris.legacybalance.domain.Balance;

public interface BalancePort {

    Mono<Balance> getUserBalance(String userId);
}

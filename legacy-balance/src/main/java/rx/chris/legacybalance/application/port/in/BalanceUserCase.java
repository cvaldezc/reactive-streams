package rx.chris.legacybalance.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.legacybalance.domain.Balance;

public interface BalanceUserCase {

    Mono<Balance> getBalance(String user);

}

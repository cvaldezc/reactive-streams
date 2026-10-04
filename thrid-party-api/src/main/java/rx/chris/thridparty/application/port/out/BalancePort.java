package rx.chris.thridparty.application.port.out;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Balance;

public interface BalancePort {

    Mono<Balance> getUserBalance(String userId);
}

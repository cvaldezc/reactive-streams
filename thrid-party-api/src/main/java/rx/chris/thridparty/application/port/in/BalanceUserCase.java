package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Balance;

public interface BalanceUserCase {

    Mono<Balance> getBalance(String user);

}

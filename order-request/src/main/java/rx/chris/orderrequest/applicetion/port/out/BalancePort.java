package rx.chris.orderrequest.applicetion.port.out;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface BalancePort {

    Mono<BigDecimal> getBalance(String userId);
}

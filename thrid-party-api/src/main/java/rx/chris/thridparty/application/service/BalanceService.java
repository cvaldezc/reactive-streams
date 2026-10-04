package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.BalanceUserCase;
import rx.chris.thridparty.application.port.out.BalancePort;
import rx.chris.thridparty.domain.Balance;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService implements BalanceUserCase {

    private final BalancePort balancePort;

    @Override
    public Mono<Balance> getBalance(String user) {
        return balancePort.getUserBalance(user)
                .doOnNext(balance -> log.info("Get Balance {}", balance))
                .doOnError(e -> log.error("Get Balance error", e));
    }
}

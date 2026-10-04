package rx.chris.thridparty.infraestructure.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.out.BalancePort;
import rx.chris.thridparty.domain.Balance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Component
@RequiredArgsConstructor
public class BalanceDBAdapter implements BalancePort {

    @Override
    public Mono<Balance> getUserBalance(String userId) {
        return Mono.fromCallable(() -> {
                    var amount = BigDecimal.valueOf(new Random().nextDouble(1000)).setScale(2, RoundingMode.HALF_EVEN);
                    return new Balance(userId, amount);
                })
                .delayElement(Duration.ofSeconds(new Random().nextInt(6)))
                .timeout(Duration.ofSeconds(5))
                .onErrorMap(TimeoutException.class, e -> new RuntimeException("Timeout legacy", e));

    }
}

package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.BalanceUserCase;
import rx.chris.thridparty.infrastructure.in.rest.dto.WalletBalance;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final BalanceUserCase balanceUserCase;

    @GetMapping("/{userId}/balance")
    public Mono<WalletBalance> getBalance(@PathVariable("userId") String userId) {
        return balanceUserCase.getBalance(userId)
                .map(balance -> new WalletBalance(balance.amount()));
    }
}

package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.BalanceUserCase;
import rx.chris.thridparty.application.port.in.WalletsUseCase;
import rx.chris.thridparty.application.service.WalletService;
import rx.chris.thridparty.domain.Wallet;
import rx.chris.thridparty.infrastructure.in.rest.dto.WalletBalance;
import rx.chris.thridparty.infrastructure.in.rest.dto.WalletRequest;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final BalanceUserCase balanceUserCase;
    private final WalletsUseCase walletsUseCase;

    @GetMapping("/{userId}/balance")
    public Mono<WalletBalance> getBalance(@PathVariable("userId") String userId) {
        return balanceUserCase.getBalance(userId)
                .map(balance -> new WalletBalance(balance.amount()));
    }

    @PostMapping
    public Mono<Wallet> createWallet(@RequestBody WalletRequest request) {
        return walletsUseCase.createWallet(request.customerId());
    }
}

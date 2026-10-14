package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.WalletsUseCase;
import rx.chris.thridparty.domain.Wallet;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletService implements WalletsUseCase {

    @Override
    public Mono<Wallet> createWallet(Long customerId) {
        return Mono.fromCallable(() -> new Wallet(UUID.randomUUID().toString()))
                .doOnNext(w -> log.info("Creating new wallet {} for customerId: {}", w, customerId));
    }
}

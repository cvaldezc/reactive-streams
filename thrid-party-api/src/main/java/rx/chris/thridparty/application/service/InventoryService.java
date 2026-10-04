package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.InventoryUseCase;
import rx.chris.thridparty.infrastructure.in.rest.dto.InventoryResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService implements InventoryUseCase {

    private final Random random = new Random();

    @Override
    public Mono<InventoryResponse> getInventory(String productId) {
        return Mono.fromCallable(() -> {
                    var price = BigDecimal.valueOf(random.nextDouble(1000));
                    return new InventoryResponse(random.nextBoolean(), price.setScale(2, RoundingMode.HALF_DOWN));
                })
                .delayElement(Duration.ofSeconds(random.nextInt(2)))
                .doOnNext(response -> log.info("Getting inventory response: {}", response));
    }
}

package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.PricingUseCase;
import rx.chris.thridparty.domain.Pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingService implements PricingUseCase {

    private final Random random = new Random();

    @Override
    public Mono<Pricing> getQuotation(double weight, String zone) {
        return Mono.fromCallable(() -> random.nextDouble(100))
                .map(BigDecimal::valueOf)
                .map(price -> price.setScale(2, RoundingMode.HALF_DOWN))
                .map(Pricing::new)
                .doOnNext(price -> log.info("Pricing: {} for weight: {} and zone: {}", price,  weight, zone));
    }
}

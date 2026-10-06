package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.CourierUseCase;
import rx.chris.thridparty.domain.AvailableCourier;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService implements CourierUseCase {

    private final Random random = new Random();
    private final List<String> couriers = List.of("HTC", "FEDEX", "HDL", "AWS");

    @Override
    public Mono<AvailableCourier> getAvailableCourier(String destination) {
        return Mono.fromCallable(() -> {
            var index = random.nextInt(couriers.size());
            return new AvailableCourier(couriers.get(index), random.nextBoolean());
        }).delayElement(Duration.ofSeconds(random.nextInt(7)))
                .doOnNext(courier -> log.info("Courier: " + courier));
    }
}

package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.EventUseCae;
import rx.chris.thridparty.domain.Event;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService implements EventUseCae {

    private final Random random = new Random();

    @Override
    public Mono<Event> getAvailabilityEvents(String eventId, Long quantity) {
        return Mono.fromCallable(() -> {
            var hasStock = random.nextBoolean();
            var price = BigDecimal.valueOf(random.nextDouble(100)).setScale(2, RoundingMode.HALF_DOWN);
            return new Event(hasStock, price);
        }).doOnNext(event -> log.info("Event price {}, for {} and {}", event, eventId, quantity));
    }
}

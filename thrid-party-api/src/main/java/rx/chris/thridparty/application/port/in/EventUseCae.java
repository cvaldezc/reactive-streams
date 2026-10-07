package rx.chris.thridparty.application.port.in;

import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Event;

public interface EventUseCae {

    Mono<Event> getAvailabilityEvents(String eventId, Long quantity);
}

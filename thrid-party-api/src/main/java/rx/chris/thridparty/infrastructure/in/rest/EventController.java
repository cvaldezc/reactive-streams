package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.EventUseCae;
import rx.chris.thridparty.application.service.EventService;
import rx.chris.thridparty.domain.Event;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventUseCae eventUseCae;

    @GetMapping("/{eventId}/availability")
    public Mono<Event> getAvailabilityEvents(@PathVariable("eventId") String eventId, @RequestParam("qty") Long quantity) {
        return eventUseCae.getAvailabilityEvents(eventId, quantity);
    }
}

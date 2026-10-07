package rx.chris.ticketreservation.expose;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.ticketreservation.model.Reservation;
import rx.chris.ticketreservation.model.ReservationRequest;
import rx.chris.ticketreservation.service.TicketReservationService;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketReservationController {

    private final TicketReservationService ticketReservationService;

    @PostMapping("/reserve")
    Mono<Reservation> createTicketReservation(@RequestBody ReservationRequest request) {
        return ticketReservationService.createTicketReservation(request);
    }
}

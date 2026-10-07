package rx.chris.ticketreservation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalController {


    @ExceptionHandler(TicketReservationException.class)
    public Mono<ErrorResponse> handleConflict(TicketReservationException ex) {
        return Mono.just(new ErrorResponse(ex.getMessage()));
    }
}

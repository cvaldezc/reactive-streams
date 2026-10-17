package rx.chris.ordersmanager.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(CustomBusinessException.class)
    public Mono<ErrorResponse> handleException(CustomBusinessException ex) {
        return Mono.just(new ErrorResponse(ex.getMessage()));
    }
}

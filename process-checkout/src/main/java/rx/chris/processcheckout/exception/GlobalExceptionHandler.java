package rx.chris.processcheckout.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import rx.chris.processcheckout.model.CheckoutResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserOrInventoryException.class)
    public Mono<ErrorResponse> handleException(UserOrInventoryException ex) {
        return Mono.just(new ErrorResponse(ex.getMessage()));
    }
}

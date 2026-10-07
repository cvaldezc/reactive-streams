package rx.chris.shippingrouter;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;


@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(CustomException.class)
    public Mono<ErrorResponse> handleException(CustomException e) {
        return Mono.just(new ErrorResponse(e.getMessage()));
    }

    private record ErrorResponse(String message) {
    }
}

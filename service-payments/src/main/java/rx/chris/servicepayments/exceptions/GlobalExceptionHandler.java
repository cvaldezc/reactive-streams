package rx.chris.servicepayments.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnalyzeFraudException.class)
    public Mono<ErrorResponse> handleAnalyzeFraud(AnalyzeFraudException ex) {
        return Mono.just(new ErrorResponse("FAILED", ex.getMessage()));
    }

    public static record ErrorResponse(String error, String message) {}
}

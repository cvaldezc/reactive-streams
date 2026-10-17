package rx.chris.kycwallet.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import rx.chris.kycwallet.model.dto.KycResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Mono<ErrorResponse> handleException(CustomException e) {
        return Mono.just(new ErrorResponse(e.getMessage()));
    }


}

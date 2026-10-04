package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.infrastructure.in.rest.dto.UserStatus;

public interface UserUseCase {

    Mono<UserStatus> getUserStatus(String userId);
}

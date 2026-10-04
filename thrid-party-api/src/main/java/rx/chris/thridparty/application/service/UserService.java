package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.UserUseCase;
import rx.chris.thridparty.infrastructure.in.rest.dto.UserStatus;

import java.time.Duration;
import java.util.List;
import java.util.Random;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final Random random = new Random();
    private final List<String> USER_STATUS = List.of("ACTIVE", "INACTIVE", "BLOCKED", "DELETED");

    @Override
    public Mono<UserStatus> getUserStatus(String userId) {
        return Mono.fromCallable(() -> {
                    var index = random.nextInt(USER_STATUS.size());
                    return USER_STATUS.get(index);
                })
                .map(UserStatus::new)
                .delayElement(Duration.ofSeconds(random.nextInt(7)))
                .doOnNext(userStatus -> log.info("UserStatus {}", userStatus));
    }
}

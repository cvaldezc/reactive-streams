package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.service.UserService;
import rx.chris.thridparty.infrastructure.in.rest.dto.UserStatus;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class LegacyUserController {

    private final UserService userService;

    @GetMapping("/{userId}/status")
    Mono<UserStatus> getUserStatus(@PathVariable("userId") String userId) {
        return userService.getUserStatus(userId);
    }
}

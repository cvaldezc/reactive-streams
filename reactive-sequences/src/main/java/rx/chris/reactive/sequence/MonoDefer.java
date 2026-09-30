package rx.chris.reactive.sequence;

import reactor.core.publisher.Mono;

public class MonoDefer {

    public static void main(String[] args) {
        requestUserData("123")
                .subscribe(System.out::println);
    }

    static Mono<User> requestUserData(String sessionId) {
        return Mono.defer(() ->
                isValidSession(sessionId)
                ? Mono.fromCallable(() -> requestUser(sessionId))
                        : Mono.error(new Throwable("Invalid user session"))
        );
    }

    static boolean isValidSession(String sessionId) {
        return !sessionId.isBlank();
    }

    static User requestUser(String sessionId) {
        return new User(sessionId, "Chris");
    }
}

record User(String id, String name) {
}
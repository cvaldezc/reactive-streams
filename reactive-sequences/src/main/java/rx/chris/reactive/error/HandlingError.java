package rx.chris.reactive.error;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HandlingError {

    private final static Random random = new Random();

    static Flux<String> recommendedBooks(String userId) {
        return Flux.defer(() -> {
            if (random.nextInt(10) < 7) {
                return Flux.<String>error(new RuntimeException("books not recommended"))
                        .delaySequence(Duration.ofMillis(100));
            } else {
                return Flux.just("Blue Mars", "The Expanse")
                        .delayElements(Duration.ofMillis(50));
            }
        }).doOnSubscribe(s -> System.out.println(time() + "Request for: " + userId));
    }

    public static void main(String[] args) throws InterruptedException {

        Flux.just("user-1")
                .flatMap(user ->
                        recommendedBooks(user)
                                .retryWhen(Retry.backoff(5, Duration.ofMillis(100)))
                                .timeout(Duration.ofSeconds(3))
                                .onErrorResume(e -> Flux.just("The Martian"))
                )
                .subscribe(
                        b -> System.out.println(time() +"onNext: " + b),
                        e -> System.out.println(time() +"onError:  " + e),
                        () -> System.out.println(time() +"onComplete")
                );

        TimeUnit.SECONDS.sleep(3);
    }

    private static String time() {
        return "[" + LocalTime.now().toString() + "] ";
    }


}

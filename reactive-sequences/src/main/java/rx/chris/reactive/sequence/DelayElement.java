package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class DelayElement {


    public static void main(String[] args) throws InterruptedException {
        Flux.just("user-1",  "user-2", "user-3")
                .flatMap(u -> requestBooks(u).map(b -> u + "/" + b))
                .subscribe(System.out::println);

        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(1))
                        .sample(Duration.ofMillis(20))
                                .subscribe(e -> System.out.println("OnNext: " + e));

        Thread.sleep(5000L);
    }

    static Flux<String> requestBooks(String user) {
        return Flux.range(1, new Random().nextInt(3) + 1)
                .map(i -> "book-" + i)
                .delayElements(Duration.ofMillis(3));
    }
}

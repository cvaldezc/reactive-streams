package rx.chris.reactive.stream;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CachingElements {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.range(0, 2)
                .doOnSubscribe(s -> System.out.println("new Subscription for the cold publisher"));

        Flux<Integer> cachedSource = source.cache(Duration.ofSeconds(1));

        cachedSource.subscribe(e -> System.out.println("[S1] onNext: " + e));
        cachedSource.subscribe(e -> System.out.println("[S2] onNext: " + e));

        TimeUnit.SECONDS.sleep(2);

        cachedSource.subscribe(e -> System.out.println("[S3] onNext: " + e));
    }
}

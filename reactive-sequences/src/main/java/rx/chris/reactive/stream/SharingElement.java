package rx.chris.reactive.stream;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit.*;

public class SharingElement {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.range(0, 5)
                .delayElements(Duration.ofMillis(100))
                .doOnSubscribe(s -> System.out.println("new subscription for the cold publisher"));

        Flux<Integer> cachedSource = source.share();

        cachedSource.subscribe(e -> System.out.println("[S1] onNext: " + e));
        TimeUnit.MILLISECONDS.sleep(400);

        cachedSource.subscribe(e -> System.out.println("[S2] onNext: " + e));

        TimeUnit.SECONDS.sleep(2);
     }
}

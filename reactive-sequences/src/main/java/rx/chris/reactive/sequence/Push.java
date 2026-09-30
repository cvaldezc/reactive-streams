package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Push {

    public static void main(String[] args) throws InterruptedException {
        Flux.push(emitter -> IntStream
                .range(2000, 3000)
                .forEach(emitter::next)
                )
                .delayElements(Duration.ofMillis(2))
        .subscribe(e -> System.out.println("OnNext: "+e));



        Flux.create(emitter -> {
            emitter.onDispose(() -> System.out.println("Disposed"));
            // push events
            emitter.next("Hello from Flux create");
        })
                .subscribe(e -> System.out.println("OnNext: "+e));

        TimeUnit.SECONDS.sleep(5L);
    }
}

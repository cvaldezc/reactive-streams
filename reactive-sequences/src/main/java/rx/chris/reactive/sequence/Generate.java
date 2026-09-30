package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Generate {

    public static void main(String[] args) throws InterruptedException {
        Flux.generate(
                () -> Tuples.of(0L, 1L),
                (state, sink) -> {
                    System.out.println("Generated value: " + state.getT2());
                    sink.next(state.getT2());
                    long newValue = state.getT1() + state.getT2();
                    return Tuples.of(state.getT2(), newValue);
                }
        )
                .delayElements(Duration.ofMillis(2))
                .take(7)
                .subscribe(e -> System.out.println("onNext: " + e));

        TimeUnit.SECONDS.sleep(5);
    }
}

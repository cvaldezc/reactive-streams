package rx.chris.reactive.dealingtime;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Elapse {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(0, 5)
                .delayElements(Duration.ofMillis(100))
                .elapsed()
                .subscribe(e -> System.out.println("Elapsed " + e.getT1() + " ms: " +  e.getT2()));

        TimeUnit.SECONDS.sleep(2);
    }
}

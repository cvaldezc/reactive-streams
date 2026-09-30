package rx.chris.reactive.sequence;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DisposableFlux {

    public static void main(String[] args) throws InterruptedException {
        Disposable disposable = Flux.interval(Duration.ofMillis(50))
                .subscribe(
                        data -> System.out.println("onNext: " + data)
                );
        Thread.sleep(200);
        disposable.dispose();
    }
}

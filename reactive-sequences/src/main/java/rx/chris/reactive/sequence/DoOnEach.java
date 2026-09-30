package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

public class DoOnEach {

    public static void main(String[] args) {
        Flux.just(1,2,3)
                .concatWith(Flux.error(new RuntimeException("With error")))
                .doOnEach(s -> System.out.println("signal: " + s))
                .subscribe();
    }
}

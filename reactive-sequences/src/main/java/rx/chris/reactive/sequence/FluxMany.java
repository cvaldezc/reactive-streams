package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

public class FluxMany {

    public static void main(String[] args) {
        Flux.just(1, 2, 3)
                .thenMany(Flux.just(4, 5))
                .subscribe(e -> System.out.println("onNext: " + e));
    }
}

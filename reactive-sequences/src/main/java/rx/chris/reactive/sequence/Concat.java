package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

public class Concat {

    public static void main(String[] args) {
        Flux.concat(
                Flux.range(1, 3),
                Flux.range(4, 2),
                Flux.range(6, 5)
        )
                .subscribe(e -> System.out.println("onNext: " + e));
    }
}

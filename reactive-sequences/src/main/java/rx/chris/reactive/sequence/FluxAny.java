package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

public class FluxAny {

    public static void main(String[] args) {
        Flux.just(3, 5, 7, 9, 11, 15, 16, 17)
                .any(e -> e % 2 == 0)
                .subscribe(hasEvens -> System.out.println("Has evens : " + hasEvens));
    }
}

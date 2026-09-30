package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

public class FluxReduce {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .reduce(0, (acc, elem) -> acc + elem)
                .subscribe(result -> System.out.printf("Result: " + result));
    }
}

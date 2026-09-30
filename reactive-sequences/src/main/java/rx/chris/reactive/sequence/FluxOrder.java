package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

import java.util.Comparator;

public class FluxOrder {

    public static void main(String[] args) {
        Flux.just(1, 6, 2, 8, 3, 1, 5, 1)
                .collectSortedList(Comparator.reverseOrder())
                .subscribe(System.out::println);
    }
}

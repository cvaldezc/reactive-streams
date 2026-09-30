package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

public class Buffer {

    public static void main(String[] args) {
        Flux.range(1, 13)
                .buffer(4)
                .subscribe(e -> System.out.println("onNext: " + e));
    }
}

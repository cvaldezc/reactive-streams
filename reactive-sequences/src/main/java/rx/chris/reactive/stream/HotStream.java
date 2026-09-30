package rx.chris.reactive.stream;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotStream {

    public static void main(String[] args) {
        Flux<Integer> source = Flux.range(0, 3)
                .doOnSubscribe(s -> System.out.println("new subscription for the cold publisher "));


        ConnectableFlux<Integer> conn = source.publish();

        conn.subscribe(e -> System.out.println("[Subscriber 1] onNext: " + e));
        conn.subscribe(e -> System.out.println("[Subscriber 2] onNext: " + e));

        System.out.println("all subscribers are ready, connecting");

        conn.connect();
    }
}

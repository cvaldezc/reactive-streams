package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class FluxSequence {

    public static void main(String[] args) {
        Flux<String> stream1 = Flux.just("Hello", "world");
        stream1.subscribe(System.out::println);
        System.out.println("--------------------------------------------------");

        Flux<Integer> stream2 = Flux.fromArray(new Integer[]{1, 2, 3});
        stream2.subscribe(System.out::println);
        System.out.println("--------------------------------------------------");

        Flux<Integer> stream3 = Flux.fromIterable(Arrays.asList(new Integer[]{9, 8, 7}));
        stream3.subscribe(System.out::println);
        System.out.println("--------------------------------------------------");

        Flux<Integer> stream4 = Flux.range(2010, 9);
        stream4.subscribe(System.out::println);
        System.out.println("--------------------------------------------------");

        Flux<String> empty = Flux.empty();
        empty.subscribe(System.out::println);
        Flux<String> never = Flux.never();
        never.subscribe(System.out::println);
        System.out.println("--------------------------------------------------");

        Flux.just("A", "B", "C")
                .subscribe(
                        data -> System.out.println("onNext: " + data),
                        err -> {/* ignored*/},
                        () -> System.out.println("onComplete")
                );
        System.out.println("--------------------------------------------------");

        Flux.range(1, 100)
                .subscribe(
                        data -> System.out.println("onNext: " + data),
                        err -> System.out.println("Error"),
                        () -> System.out.println("onComplete"),
                        subscription -> {
                            subscription.request(4);
                            subscription.cancel();
                        }
                );
    }

}

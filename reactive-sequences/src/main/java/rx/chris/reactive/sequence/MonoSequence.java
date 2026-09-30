package rx.chris.reactive.sequence;

import reactor.core.publisher.Mono;

import java.util.Optional;

public class MonoSequence {

    public static void main(String[] args) {
        Mono<String> stream5 = Mono.just("One");
        Mono<String> stream6 = Mono.justOrEmpty(null);
        Mono<String> stream7 = Mono.justOrEmpty(Optional.empty());

        stream5.subscribe(System.out::println);
        stream6.subscribe(System.out::println);
        stream7.subscribe(System.out::println);

        Mono<String> error = Mono.error(new RuntimeException("Unknown id"));
        error.subscribe(System.out::println);
    }
}

package rx.chris.reactive.stream;

import reactor.core.publisher.Flux;

import java.util.UUID;

public class ColdPublisher {

    public static void main(String[] args) {
        Flux<String> coldPublisher = Flux.defer( () -> {
            System.out.println("Generating new items");
            return Flux.just(UUID.randomUUID().toString());
        });

        System.out.println("No data was generated so far");
        coldPublisher.subscribe(e -> System.out.println("onNext: " + e));
        coldPublisher.subscribe(e -> System.out.println("onNext: " + e));
        System.out.println("data was generated twice for two subscribers");
    }

}

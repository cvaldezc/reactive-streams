package rx.chris.reactive.transaction;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

public class Transaction {

    private static final Random random = new Random();
    private final int id;

    public Transaction(int id) {
        this.id = id;
        System.out.println("[T: " + id + " created]");
    }

    public static Mono<Transaction> beginTransaction() {
        return Mono.defer(() -> Mono.just(new Transaction(random.nextInt(1000))));
    }

    public Flux<String> insertRows(Publisher<String> rows) {
        return Flux.from(rows)
                .delayElements(Duration.ofMillis(100))
                .flatMap(row -> {
                    if (random.nextInt(10) < 2) {
                        return Mono.error(new RuntimeException("Error: " + row));
                    } else {
                        return Mono.just(row);
                    }
                });
    }

    public Mono<Void> commit() {
        return Mono.defer(() -> {
            System.out.println("[T: " + id + " committed]");
            if (random.nextBoolean()) {
                return Mono.empty();
            } else {
                return Mono.error(new RuntimeException("Conflict"));
            }
        });
    }

    public Mono<Void> rollback() {
        return Mono.defer(() -> {
            System.out.println("[T: " + id + "] rollbacked");
            if (random.nextBoolean()) {
                return Mono.empty();
            } else {
                return Mono.error(new RuntimeException("Conn error"));
            }
        });
    }

}

package rx.chris.reactive.transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class TransactionApp {

    public static void main(String[] args) throws InterruptedException {
        Flux.usingWhen(
                        Transaction.beginTransaction(),
                        transaction -> transaction.insertRows(Flux.just("A", "B", "C")),
                        Transaction::commit,
                        (transaction, throwable) -> transaction.rollback(),
                        (t) -> {
                            System.out.println("[Async Cancel]");
                            return Mono.empty();
                        }
                )
                .subscribe(
                        d -> System.out.println("onNext: " + d),
                        e -> System.out.println("onError: " + e.getMessage()),
                        () -> System.out.println("onComplete")
                );

        TimeUnit.SECONDS.sleep(1);
    }
}

package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class WindowUntil {

    public static void main(String[] args) {
        Flux<Flux<Integer>> window = Flux.range(101, 20)
                .windowUntil(WindowUntil::isPrime, true);

        window.subscribe(
                win -> win.collectList()
                        .subscribe(e -> System.out.println("window: "+ e))
        );
    }

    static boolean isPrime(int n) {
        return n > 1  && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }
}

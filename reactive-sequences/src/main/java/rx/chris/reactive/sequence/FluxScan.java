package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class FluxScan {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .scan(0, (acc, elem) -> acc + elem)
                .subscribe(result -> System.out.println("Result: " + result));

        System.out.println("--------------------------");

        int bucketSize = 5;
        Flux.range(1, 500)
                .index()
                .scan(
                        new int[bucketSize],
                        (acc, elem) -> {
                            acc[(int)(elem.getT1() % bucketSize)] = elem.getT2();
                            return acc;
                        }
                )
                .skip(bucketSize)
                .map(array -> Arrays.stream(array).sum() * 1.0 / bucketSize)
                .subscribe(av -> System.out.println("Running Average: " + av));
    }
}

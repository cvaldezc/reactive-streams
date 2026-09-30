package rx.chris.reactive.sequence;

import reactor.core.publisher.Flux;

import java.time.Instant;

public class FluxTuple {

    public static void main(String[] args) {
        Flux.range(2018, 5)
                .timestamp()
                .index()
                .subscribe(e -> System.out.println(
                        "index: " + e.getT1()
                        + " ts: " + Instant.ofEpochMilli(e.getT2().getT1())
                        + " "+ e.getT2().getT2()
                        )
                );
    }
}

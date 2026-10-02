package rx.chris.productstream.adapter.httpclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import rx.chris.productstream.model.Product;

import java.time.Duration;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CategoryAdapter {

    public Mono<Boolean> hasCategoryActive(Product product) {
        return Mono.just(new Random().nextBoolean())
                .delayElement(Duration.ofMillis(500));
    }
}

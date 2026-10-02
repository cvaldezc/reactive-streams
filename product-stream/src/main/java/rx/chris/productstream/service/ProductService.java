package rx.chris.productstream.service;

import reactor.core.publisher.Mono;
import rx.chris.productstream.model.Product;

public interface ProductService {

    Mono<Product> saveProduct(Product product);

    Mono<Product> saveWithCategory(Product product);
}

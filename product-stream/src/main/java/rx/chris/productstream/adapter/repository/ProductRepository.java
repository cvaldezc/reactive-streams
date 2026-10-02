package rx.chris.productstream.adapter.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import rx.chris.productstream.adapter.entity.ProductEntity;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long> {
}

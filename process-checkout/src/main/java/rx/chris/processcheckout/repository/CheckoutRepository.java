package rx.chris.processcheckout.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends ReactiveCrudRepository<CheckoutEntity, Long> {
}

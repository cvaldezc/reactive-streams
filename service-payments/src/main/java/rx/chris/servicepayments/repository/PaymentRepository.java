package rx.chris.servicepayments.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import rx.chris.servicepayments.model.Payment;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<PaymentEntity, Long> {
}

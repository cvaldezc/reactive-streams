package rx.chris.shippingrouter.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends ReactiveCrudRepository<ShipmentEntity, Long> {
}

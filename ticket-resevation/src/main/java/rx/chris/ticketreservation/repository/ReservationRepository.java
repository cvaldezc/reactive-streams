package rx.chris.ticketreservation.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReservationRepository extends ReactiveCrudRepository<ReservationEntity, Long> {
}

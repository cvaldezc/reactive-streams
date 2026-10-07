package rx.chris.ticketreservation.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("reservations")
public class ReservationEntity {
    @Id
    private Long id;
    private String eventId;
    private String userId;
    private BigDecimal totalAmount;
}

package rx.chris.shippingrouter.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("shipments")
public class ShipmentEntity {
    @Id
    private Long id;
    private String orderId;
    private String courierName;
    private BigDecimal finalCost;
}

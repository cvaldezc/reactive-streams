package rx.chris.orderrequest.infra.adatper.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("orders")
@Data
public class OrderEntity {
    @Id
    private Long id;
    private String userId;
    private String product;
    private BigDecimal price;
    private String observation;
}

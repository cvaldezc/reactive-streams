package rx.chris.billingorder.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("orders")
public class OrderEntity {

    @Id
    private Long id;
    private String userId;
    private String product;
    private BigDecimal price;
    private String status;
    private String observation;
}

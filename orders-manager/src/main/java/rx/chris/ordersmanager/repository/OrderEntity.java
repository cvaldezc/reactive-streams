package rx.chris.ordersmanager.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
@Data
public class OrderEntity {

    @Id
    private Long id;
    private String customerId;
    private String productId;
    private Long quantity;
    private String status;
}

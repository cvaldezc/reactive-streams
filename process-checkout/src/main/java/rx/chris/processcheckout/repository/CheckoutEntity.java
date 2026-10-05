package rx.chris.processcheckout.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("checkout")
@Data
public class CheckoutEntity {

    @Id
    private Long id;

    private String userId;
    private String productId;
    private BigDecimal amountPaid;
}

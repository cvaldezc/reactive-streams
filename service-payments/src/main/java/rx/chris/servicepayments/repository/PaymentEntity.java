package rx.chris.servicepayments.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Table("payments")
public class PaymentEntity {

    @Id
    private Long id;
    private String accountId;
    private BigDecimal amount;
    private String status;
}

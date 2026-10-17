package rx.chris.kycwallet.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("customers")
public class Customer {

    @Id
    private Long id;
    private Long documentId;
    private String email;
    private String walletId;
    private String status;
}

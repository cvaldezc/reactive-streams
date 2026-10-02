package rx.chris.servicepayments.model;

import java.math.BigDecimal;

public record Payment(Long id, String accountId, BigDecimal amount, String status) {
}

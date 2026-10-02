package rx.chris.servicepayments.model.dto;

import java.math.BigDecimal;

public record PaymentRequest(String accountId, BigDecimal amount) {

}

package rx.chris.billingorder.model.dto;

import java.math.BigDecimal;

public record BalanceResponse(String userId, BigDecimal amount) {
}

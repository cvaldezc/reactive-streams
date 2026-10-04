package rx.chris.thridparty.infrastructure.in.rest.dto;

import java.math.BigDecimal;

public record BalanceResponse(String userId, BigDecimal amount) {
}

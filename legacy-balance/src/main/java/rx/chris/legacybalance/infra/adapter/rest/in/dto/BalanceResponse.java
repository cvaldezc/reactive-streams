package rx.chris.legacybalance.infra.adapter.rest.in.dto;

import java.math.BigDecimal;

public record BalanceResponse(String userId, BigDecimal amount) {
}

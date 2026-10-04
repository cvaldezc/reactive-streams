package rx.chris.thridparty.infraestructure.in.rest.dto;

import java.math.BigDecimal;

public record BalanceResponse(String userId, BigDecimal amount) {
}

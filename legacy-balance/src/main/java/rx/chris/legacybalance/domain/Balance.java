package rx.chris.legacybalance.domain;

import rx.chris.legacybalance.application.port.in.BalanceUserCase;

import java.math.BigDecimal;

public record Balance(String userId, BigDecimal amount) {
}

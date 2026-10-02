package rx.chris.orderrequest.infra.adatper.http.dto;

import java.math.BigDecimal;

public record Balance(String userId, BigDecimal amount) {
}

package rx.chris.orderrequest.infra.adatper.web.dto;

import java.math.BigDecimal;

public record OrderRequest(String userId, BigDecimal price, String product) {
}

package rx.chris.billingorder.model.dto;

import java.math.BigDecimal;

public record OrderRequest(String userId, String product, BigDecimal price) {
}

package rx.chris.processcheckout.model;

import java.math.BigDecimal;

public record CheckoutResponse(Long id, String userId, String productId, BigDecimal amountPaid) {
}

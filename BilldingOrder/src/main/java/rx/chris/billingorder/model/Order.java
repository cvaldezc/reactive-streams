package rx.chris.billingorder.model;

import java.math.BigDecimal;

public record Order(Long id, String userId, String product, BigDecimal price, String status, String observation) {
    public Order(String userId, String product, BigDecimal price) {
        this(null, userId, product, price, null, null);
    }
}

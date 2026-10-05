package rx.chris.processcheckout.model;

import java.math.BigDecimal;

public record Inventory(Boolean available, BigDecimal price) {
}

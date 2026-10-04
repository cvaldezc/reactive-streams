package rx.chris.thridparty.infrastructure.in.rest.dto;

import java.math.BigDecimal;

public record InventoryResponse(Boolean available, BigDecimal price) {
}

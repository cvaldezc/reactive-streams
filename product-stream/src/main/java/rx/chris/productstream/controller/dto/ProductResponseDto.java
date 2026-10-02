package rx.chris.productstream.controller.dto;

import java.math.BigDecimal;

public record ProductResponseDto(Long id, String name, BigDecimal pricePen, BigDecimal priceUsd, BigDecimal rate, Boolean isActiveCategory) {
}

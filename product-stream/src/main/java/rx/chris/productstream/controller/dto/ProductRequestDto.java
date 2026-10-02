package rx.chris.productstream.controller.dto;

import java.math.BigDecimal;

public record ProductRequestDto(String name, BigDecimal priceUsd) {
}

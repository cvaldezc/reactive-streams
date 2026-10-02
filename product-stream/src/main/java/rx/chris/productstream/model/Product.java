package rx.chris.productstream.model;

import java.math.BigDecimal;

public record Product(Long id, String name, BigDecimal priceUsd, BigDecimal pricePen, BigDecimal rate, Boolean isActiveCategory) {
  public Product(Long id, String name, BigDecimal priceUsd, BigDecimal pricePen) {
        this(id, name, priceUsd, pricePen, BigDecimal.ZERO, false);
  }
}

package rx.chris.productstream.adapter.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("productos")
public class ProductEntity {

    @Id
    private Long id;
    private String name;
    private BigDecimal priceUsd;
    private BigDecimal pricePen;
    private BigDecimal rate;
    private Boolean isActiveCategory;
}

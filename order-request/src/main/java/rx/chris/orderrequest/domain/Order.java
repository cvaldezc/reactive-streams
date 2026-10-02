package rx.chris.orderrequest.domain;

import java.math.BigDecimal;

public record Order(Long id,
                    String userId,
                    String product,
                    BigDecimal price,
                    String observation) {
}

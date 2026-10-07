package rx.chris.thridparty.domain;

import java.math.BigDecimal;

public record Event(Boolean hasStock, BigDecimal unitPrice) {
}

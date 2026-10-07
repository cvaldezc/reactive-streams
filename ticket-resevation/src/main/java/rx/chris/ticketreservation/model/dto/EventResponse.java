package rx.chris.ticketreservation.model.dto;

import java.math.BigDecimal;

public record EventResponse(Boolean hasStock,
                            BigDecimal unitPrice) {
}

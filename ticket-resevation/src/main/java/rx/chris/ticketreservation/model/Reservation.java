package rx.chris.ticketreservation.model;

import java.math.BigDecimal;

public record Reservation(Long id, String eventId, String userId, BigDecimal totalAmount) {
}

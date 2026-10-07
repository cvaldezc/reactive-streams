package rx.chris.ticketreservation.model;

public record ReservationRequest(String eventId, String userId, Long quantity) {
}

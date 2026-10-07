package rx.chris.ticketreservation.exception;

import java.io.Serializable;

public record ErrorResponse(String message) implements Serializable {
}

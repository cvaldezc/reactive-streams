package rx.chris.shippingrouter.model;

import java.math.BigDecimal;

public record RouteResponse(String courierName, String orderId, BigDecimal cost) {
}

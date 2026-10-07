package rx.chris.shippingrouter.model;

public record ShippingRequest(String orderId, double weight, String destination) {
}

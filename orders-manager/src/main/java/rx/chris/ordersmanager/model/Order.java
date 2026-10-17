package rx.chris.ordersmanager.model;

public record Order(Long orderId, String customerId, String productId, Long quantity, String status) {
    public Order(Long orderId) {
        this(orderId, null, null, null, null);
    }
}

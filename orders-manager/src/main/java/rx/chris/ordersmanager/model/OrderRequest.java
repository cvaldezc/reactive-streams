package rx.chris.ordersmanager.model;

public record OrderRequest(String customerId, String productId, Long quantity) {
}

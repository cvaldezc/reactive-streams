package rx.chris.ordersmanager.model;

public record ReserveResponse(Boolean reserved, Boolean hasfallback) {

    public ReserveResponse(Boolean reserved) {
        this(reserved, Boolean.FALSE);
    }
}

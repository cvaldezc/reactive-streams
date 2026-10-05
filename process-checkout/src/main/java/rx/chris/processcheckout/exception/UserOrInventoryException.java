package rx.chris.processcheckout.exception;

public class UserOrInventoryException extends RuntimeException {
    public UserOrInventoryException(String message) {
        super(message);
    }
}

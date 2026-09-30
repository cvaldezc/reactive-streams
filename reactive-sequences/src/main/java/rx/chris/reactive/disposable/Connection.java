package rx.chris.reactive.disposable;

import java.util.Arrays;
import java.util.Random;

public class Connection implements AutoCloseable {

    private final Random random = new Random();

    public Iterable<String> getData() {
        if (random.nextInt(10) < 3) {
            throw  new RuntimeException("Communication error");
        }
        return Arrays.asList("Some", "data");
    }

    @Override
    public void close() {
        System.out.println("IO Connection closed");
    }

    public static Connection newConnection() {
        System.out.println("IO Connection created");
        return new Connection();
    }
}

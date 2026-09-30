package rx.chris.reactive.disposable;

import reactor.core.publisher.Flux;

public class DisableImpl {

    public static void main(String[] args) {

        System.out.println("Connection Imperative");
        System.out.println("-----------------------");
        disableImperative();
        System.out.println("-----------------------");

        Flux<String> ioRequestResults = Flux.using(
                Connection::newConnection,
                connection -> Flux.fromIterable(connection.getData()),
                Connection::close
        );

        ioRequestResults.subscribe(data ->
                        System.out.println("Received data: " + data),
                e -> System.out.println("Error: " + e.getMessage()),
                () -> System.out.println("Stream finished")
                );
    }

    static void disableImperative() {
        try (Connection conn = Connection.newConnection()) {
            conn.getData()
                    .forEach(data -> System.out.println("Received data: " + data));
        } catch (Exception e) {
            System.out.println("Error while disabling imperative: " + e.getMessage());
        }
    }
}

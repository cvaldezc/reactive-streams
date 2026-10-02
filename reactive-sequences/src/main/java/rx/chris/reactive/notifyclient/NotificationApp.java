package rx.chris.reactive.notifyclient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class NotificationApp {

    public static void main(String[] args) throws InterruptedException {
        var notificar = Flux.just("client-1")
                .flatMap(NotificationApp::obtenerCliente)
                .flatMap(user -> obtenerFacturas(user.clienteId())
                            .filter(Predicate.not(Factura::isPagada))
                        .map(factura -> Map.entry(user, factura))
                )
                .flatMap(entry -> enviarNotification(entry.getKey(), entry.getValue()))
                .then();

        notificar.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(3);
    }

    static Mono<Cliente> obtenerCliente(String clienteId) {
        System.out.println("Consulta cliente " + clienteId);
        return Mono.just(new Cliente(clienteId));
    }

    static Flux<Factura> obtenerFacturas(String clienteId) {
        return Flux.range(1, 3)
                .map(item -> new Factura("doc-"+ item, clienteId, new Random().nextBoolean()))
                .doOnNext(s -> System.out.println("obtener factura " + s));
    }

    static Mono<Void> enviarNotification(Cliente cliente, Factura factura) {
        System.out.println("Notificado " + cliente + " factura " + factura);
        return Mono.empty();
    }
}

record Cliente(String clienteId) {}
record Factura(String id, String clienteId, boolean isPagada) {}
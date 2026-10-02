package rx.chris.orderrequest.applicetion.port.out;

import reactor.core.publisher.Mono;
import rx.chris.orderrequest.domain.Order;

public interface OrderPort {

    Mono<Order> createOrder(Order order);
}

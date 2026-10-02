package rx.chris.orderrequest.applicetion.port.in;

import reactor.core.publisher.Mono;
import rx.chris.orderrequest.domain.Order;
import rx.chris.orderrequest.infra.adatper.web.dto.OrderRequest;

public interface OrderUserCase {

    Mono<Order> createOrder(OrderRequest order);
}

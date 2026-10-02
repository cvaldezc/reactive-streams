package rx.chris.orderrequest.infra.adatper.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.orderrequest.applicetion.port.in.OrderUserCase;
import rx.chris.orderrequest.domain.Order;
import rx.chris.orderrequest.infra.adatper.web.dto.OrderRequest;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUserCase orderUserCase;

    @PostMapping
    public Mono<Order> createOrder(@RequestBody OrderRequest request) {
        return orderUserCase.createOrder(request);
    }
}

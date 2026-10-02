package rx.chris.orderrequest.applicetion.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.orderrequest.applicetion.port.in.OrderUserCase;
import rx.chris.orderrequest.applicetion.port.out.BalancePort;
import rx.chris.orderrequest.applicetion.port.out.OrderPort;
import rx.chris.orderrequest.domain.Order;
import rx.chris.orderrequest.infra.adatper.web.dto.OrderRequest;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OrderService implements OrderUserCase {

    private final OrderPort orderPort;
    private final BalancePort balancePort;

    @Override
    public Mono<Order> createOrder(OrderRequest order) {
        return balancePort.getBalance(order.userId())
                .flatMap(balance -> {
                    var unsaved = unsavedOrder(order, balance);
                    return orderPort.createOrder(unsaved);
                });
    }

    private Order unsavedOrder(OrderRequest order, BigDecimal amount) {
        return new Order(null, order.userId(), order.product(), order.price(), "With balance " + amount);
    }
}

package rx.chris.orderrequest.infra.adatper.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import rx.chris.orderrequest.applicetion.port.out.OrderPort;
import rx.chris.orderrequest.domain.Order;
import rx.chris.orderrequest.infra.adatper.persistence.entity.OrderEntity;
import rx.chris.orderrequest.infra.adatper.persistence.mapper.OrderMapper;

@Component
@RequiredArgsConstructor
public class OrderDBAdapter implements OrderPort {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Mono<Order> createOrder(Order order) {
        var orderEntity = orderMapper.toOrderEntity(order);
        return orderRepository.save(orderEntity).map(orderMapper::toOrder);
    }
}

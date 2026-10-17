package rx.chris.ordersmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.ordersmanager.model.Order;
import rx.chris.ordersmanager.model.OrderRequest;
import rx.chris.ordersmanager.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Mono<Order> createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @PutMapping("/{orderId}/fulfill")
    public Mono<Order> fulfillOrder(@PathVariable("orderId") Long orderId) {
        return orderService.updateOrder(orderId);
    }
}

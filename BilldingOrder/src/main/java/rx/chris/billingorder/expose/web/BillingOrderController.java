package rx.chris.billingorder.expose.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.billingorder.model.dto.OrderRequest;
import rx.chris.billingorder.model.Order;
import rx.chris.billingorder.service.BillingOrderService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingOrderController {

    private final BillingOrderService billingOrderService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Order> billingOrder(@RequestBody OrderRequest request) {
        return billingOrderService.billingOrder(request);
    }
}

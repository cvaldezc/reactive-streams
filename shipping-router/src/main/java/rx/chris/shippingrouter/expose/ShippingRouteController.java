package rx.chris.shippingrouter.expose;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.shippingrouter.model.RouteResponse;
import rx.chris.shippingrouter.model.ShippingRequest;
import rx.chris.shippingrouter.service.ShippingRouteService;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShippingRouteController {

    private final ShippingRouteService shippingRouteService;

    @PostMapping("/route")
    Mono<RouteResponse> route(@RequestBody ShippingRequest request) {
        return shippingRouteService.handleShippingRoute(request);
    }
}

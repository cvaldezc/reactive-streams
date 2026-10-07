package rx.chris.shippingrouter.service;


import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.shippingrouter.CustomException;
import rx.chris.shippingrouter.model.CourierResponse;
import rx.chris.shippingrouter.model.QuoterResponse;
import rx.chris.shippingrouter.model.RouteResponse;
import rx.chris.shippingrouter.model.ShippingRequest;
import rx.chris.shippingrouter.repository.ShipmentEntity;
import rx.chris.shippingrouter.repository.ShipmentMapper;
import rx.chris.shippingrouter.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingRouteService {

    private final WebClient quoterClient;
    private final WebClient courierClient;
    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public Mono<RouteResponse> handleShippingRoute(ShippingRequest request) {
        return Mono.zip(getQuoterService(request), getCourierService(request))
                .filter(tuple -> tuple.getT2().available() && (tuple.getT1().price().compareTo(BigDecimal.valueOf(50)) < 0))
                .switchIfEmpty(Mono.error(new CustomException("Ruta no viable")))
                .flatMap(tuple -> {
                    var unsaved = new ShipmentEntity();
                    unsaved.setOrderId(request.orderId());
                    unsaved.setCourierName(tuple.getT2().courierName());
                    unsaved.setFinalCost(tuple.getT1().price());
                    return shipmentRepository.save(unsaved);
                })
                .map(shipmentMapper::toResponse)
                .doOnNext(response -> log.info("Shipping route response: {}", response));
    }


    private Mono<QuoterResponse> getQuoterService(ShippingRequest request) {
        return quoterClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/pricing")
                        .queryParam("weight", request.weight())
                        .queryParam("zone", request.destination()).build()
                ).retrieve()
                .bodyToMono(QuoterResponse.class)
                .doOnNext(quoterResponse -> log.info("Quoter response: {}", quoterResponse));
    }

    private Mono<CourierResponse> getCourierService(ShippingRequest request) {
        return courierClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/couriers/{destination}")
                        .build(request.destination())
                ).retrieve()
                .bodyToMono(CourierResponse.class)
                .timeout(Duration.ofSeconds(6))
                .transformDeferred(CircuitBreakerOperator.of(circuitBreakerRegistry.circuitBreaker("courier")))
                .onErrorResume((t) -> fallbackCourierService(request))
                .doOnNext(courierResponse -> log.info("Courier response: {}", courierResponse));
    }

    private Mono<CourierResponse> fallbackCourierService(ShippingRequest request) {
        log.error("fallback courier por request {}", request);
        return Mono.just(new CourierResponse("GUEST_COURIER", true));
    }
}

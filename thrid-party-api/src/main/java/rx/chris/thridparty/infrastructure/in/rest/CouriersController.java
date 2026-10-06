package rx.chris.thridparty.infrastructure.in.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.CourierUseCase;
import rx.chris.thridparty.domain.AvailableCourier;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
public class CouriersController {

    private final CourierUseCase courierUseCase;

    @GetMapping("/{destination}")
    Mono<AvailableCourier> getAvailableCourier(@PathVariable("destination") String destination) {
        return courierUseCase.getAvailableCourier(destination);
    }
}

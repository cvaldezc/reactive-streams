package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.AvailableCourier;

public interface CourierUseCase {

    Mono<AvailableCourier> getAvailableCourier(String destination);
}

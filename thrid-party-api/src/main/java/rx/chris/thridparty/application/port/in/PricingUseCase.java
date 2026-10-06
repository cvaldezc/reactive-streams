package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Pricing;

public interface PricingUseCase {

    Mono<Pricing> getQuotation(double weight, String zone);
}

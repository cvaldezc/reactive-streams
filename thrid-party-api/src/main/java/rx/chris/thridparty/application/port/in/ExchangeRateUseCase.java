package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.ExchangeRate;

public interface ExchangeRateUseCase {

    Mono<ExchangeRate> toExchangeRate(String currencyCode);
}

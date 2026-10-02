package rx.chris.ratesexchange.rates.application.ports.in;

import reactor.core.publisher.Mono;
import rx.chris.ratesexchange.rates.domain.ExchangeRate;

public interface ExchangeRateCase {

    Mono<ExchangeRate> toExchangeRate(String currencyCode);
}

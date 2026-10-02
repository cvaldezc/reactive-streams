package rx.chris.ratesexchange.rates.application.ports.out;

import reactor.core.publisher.Mono;
import rx.chris.ratesexchange.rates.domain.ExchangeRate;

public interface ExchangeRatePort {

    Mono<ExchangeRate> getExchangeRate(String baseCode);

}

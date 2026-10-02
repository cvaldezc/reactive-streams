package rx.chris.ratesexchange.rates.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.ratesexchange.rates.application.ports.in.ExchangeRateCase;
import rx.chris.ratesexchange.rates.application.ports.out.ExchangeRatePort;
import rx.chris.ratesexchange.rates.domain.ExchangeRate;

@Service
@RequiredArgsConstructor
public class ExchangeReateService implements ExchangeRateCase {

    private final ExchangeRatePort exchangeRatePort;

    @Override
    public Mono<ExchangeRate> toExchangeRate(String currencyCode) {
        return exchangeRatePort.getExchangeRate(currencyCode);
    }
}

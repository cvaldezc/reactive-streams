package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.ExchangeRateUseCase;
import rx.chris.thridparty.application.port.out.ExchangeRatePort;
import rx.chris.thridparty.domain.ExchangeRate;

@Service
@RequiredArgsConstructor
public class ExchangeReateService implements ExchangeRateUseCase {

    private final ExchangeRatePort exchangeRatePort;

    @Override
    public Mono<ExchangeRate> toExchangeRate(String currencyCode) {
        return exchangeRatePort.getExchangeRate(currencyCode);
    }
}

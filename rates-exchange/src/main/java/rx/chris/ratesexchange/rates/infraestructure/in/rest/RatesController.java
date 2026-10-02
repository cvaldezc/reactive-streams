package rx.chris.ratesexchange.rates.infraestructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.ratesexchange.rates.application.ports.in.ExchangeRateCase;
import rx.chris.ratesexchange.rates.domain.ExchangeRate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/rates")
@RequiredArgsConstructor
public class RatesController {

    private final ExchangeRateCase exchangeRateCase;
    private static final String CURRENCY_DEFAULT = "USD";
    private static final String CURRENCY_KEY = "baseCurrency";

    @PostMapping
    Mono<ExchangeRate> findExchangeRate(@RequestBody() Map<String, String> entry) {
        var currencyCode = entry.getOrDefault(CURRENCY_KEY, CURRENCY_DEFAULT);
        return exchangeRateCase.toExchangeRate(currencyCode);
    }
}

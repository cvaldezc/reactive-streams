package rx.chris.thridparty.infraestructure.in.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.ExchangeRateUseCase;
import rx.chris.thridparty.domain.ExchangeRate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/rates")
@RequiredArgsConstructor
public class ExchangeRatesController {

    private final ExchangeRateUseCase exchangeRateUseCase;
    private static final String CURRENCY_DEFAULT = "USD";
    private static final String CURRENCY_KEY = "baseCurrency";

    @PostMapping
    Mono<ExchangeRate> findExchangeRate(@RequestBody() Map<String, String> entry) {
        var currencyCode = entry.getOrDefault(CURRENCY_KEY, CURRENCY_DEFAULT);
        return exchangeRateUseCase.toExchangeRate(currencyCode);
    }
}
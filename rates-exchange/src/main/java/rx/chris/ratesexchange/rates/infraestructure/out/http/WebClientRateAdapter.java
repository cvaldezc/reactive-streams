package rx.chris.ratesexchange.rates.infraestructure.out.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.ratesexchange.rates.application.ports.out.ExchangeRatePort;
import rx.chris.ratesexchange.rates.domain.ExchangeRate;
import rx.chris.ratesexchange.rates.infraestructure.out.http.dto.ExchangeResponse;
import rx.chris.ratesexchange.rates.infraestructure.out.http.mapper.HttpMapper;

@Component
@RequiredArgsConstructor
public class WebClientRateAdapter implements ExchangeRatePort {

    private final WebClient webClient;
    private final HttpMapper httpMapper;

    @Override
    public Mono<ExchangeRate> getExchangeRate(String baseCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v6/latest/{currency}")
                        .build(baseCode))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve().bodyToMono(ExchangeResponse.class)
                .map(httpMapper::toExchangeRate);

    }
}

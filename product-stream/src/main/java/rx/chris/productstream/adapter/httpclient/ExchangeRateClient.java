package rx.chris.productstream.adapter.httpclient;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.productstream.adapter.httpclient.dto.ExchangeRateDto;
import rx.chris.productstream.adapter.httpclient.dto.RatePayload;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateClient {

    private final WebClient webClient;

    public Mono<ExchangeRateDto> getExchangeRate(String baseCurrency) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/rates").build())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .bodyValue(new RatePayload(baseCurrency))
                .retrieve()
                .bodyToMono(ExchangeRateDto.class);
    }

    public Mono<BigDecimal> getRateFrom(String baseCurrency, String currency) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/rates").build())
                .bodyValue(new RatePayload(baseCurrency))
                .retrieve()
                .bodyToMono(ExchangeRateDto.class)
                .map(exchangeRateDto -> exchangeRateDto.rates().getOrDefault(currency, BigDecimal.ZERO))
                .doOnError(e -> log.error("Fallo al obtener el rate exchange"))
                .onErrorResume(throwable ->  Mono.error(() -> new RuntimeException("Fallo la conexion al rate exchange")));
    }
}

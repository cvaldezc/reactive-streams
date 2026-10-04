package rx.chris.thridparty.infrastructure.out.http;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.out.ExchangeRatePort;
import rx.chris.thridparty.domain.ExchangeRate;
import rx.chris.thridparty.infrastructure.out.http.dto.ExchangeResponse;
import rx.chris.thridparty.infrastructure.out.http.mapper.HttpMapper;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WebClientRateAdapter implements ExchangeRatePort {

    private final WebClient webClient;
    private final HttpMapper httpMapper;

    @Value("${app.rates.url}")
    private String baseUrl;

    @Override
    public Mono<ExchangeRate> getExchangeRate(String baseCode) {
        return webClient.get()
                .uri(URI.create(baseUrl + "/v6/latest/" + baseCode))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve().bodyToMono(ExchangeResponse.class)
                .map(httpMapper::toExchangeRate);

    }
}

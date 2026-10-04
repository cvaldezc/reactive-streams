package rx.chris.thridparty.application.port.out;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.ExchangeRate;

public interface ExchangeRatePort {

    Mono<ExchangeRate> getExchangeRate(String baseCode);

}

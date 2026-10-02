package rx.chris.ratesexchange.rates.domain;

import java.time.LocalDateTime;
import java.util.Map;

public record ExchangeRate(String timeLastUpdate, String baseCode, Map<String, String> rates, String result) {
}

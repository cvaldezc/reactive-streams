package rx.chris.ratesexchange.rates.infraestructure.out.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Map;

public record ExchangeResponse(@JsonProperty("time_last_update_utc") String timeLastUpdate, @JsonProperty("base_code") String baseCode, Map<String, String> rates, String result) {
}

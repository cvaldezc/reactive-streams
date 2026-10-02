package rx.chris.productstream.adapter.httpclient.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateDto(String result, String baseCode, Map<String, BigDecimal> rates) {
}

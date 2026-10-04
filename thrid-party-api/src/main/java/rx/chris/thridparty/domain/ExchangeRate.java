package rx.chris.thridparty.domain;

import java.util.Map;

public record ExchangeRate(String timeLastUpdate, String baseCode, Map<String, String> rates, String result) {
}


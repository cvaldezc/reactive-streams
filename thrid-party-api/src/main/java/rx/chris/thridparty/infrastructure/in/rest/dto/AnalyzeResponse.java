package rx.chris.thridparty.infrastructure.in.rest.dto;

import java.math.BigDecimal;
import java.util.Map;

public record AnalyzeResponse(
        String transactionId,
        BigDecimal riskScore,
        String decision,
        Map<String, String> metadata
) {
}

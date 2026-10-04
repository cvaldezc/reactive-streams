package rx.chris.thridparty.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import rx.chris.thridparty.application.port.in.AnalyzeUseCase;
import rx.chris.thridparty.infrastructure.in.rest.dto.AnalyzeResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyzeService implements AnalyzeUseCase {

    @Override
    public Flux<AnalyzeResponse> analyzeService(String accountId) {
        return getTransaction(accountId)
                .map(trx -> Map.entry(trx, getScore(accountId)))
                .map(entry -> {
                    var decision = (entry.getValue().compareTo(BigDecimal.valueOf(0.5)) > 0) ? "APPROVED" : "REJECTED";
                    return new AnalyzeResponse(entry.getKey(), entry.getValue(), decision, Map.of("account", accountId));
                })
                .delayElements(Duration.ofSeconds(new Random().nextInt(7)))
                .doOnNext(result -> log.info("Analyze Fraud: {}", result));
    }

    private Flux<String> getTransaction(String accountId) {
        return Flux.just(String.format("T-%d", new Random().nextInt(100)));
    }

    private BigDecimal getScore(String accountId) {
        return BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.HALF_EVEN);
    }
}

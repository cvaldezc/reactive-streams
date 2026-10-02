package rx.chris.fraudapi.service;

import reactor.core.publisher.Flux;
import rx.chris.fraudapi.dto.AnalyzeResponse;

public interface AnalyzeUseCase {

    Flux<AnalyzeResponse> analyzeService(String accountId);
}

package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Flux;
import rx.chris.thridparty.infraestructure.in.rest.dto.AnalyzeResponse;

public interface AnalyzeUseCase {

    Flux<AnalyzeResponse> analyzeService(String accountId);
}

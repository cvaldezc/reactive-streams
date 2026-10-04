package rx.chris.thridparty.infraestructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import rx.chris.thridparty.infraestructure.in.rest.dto.AnalyzeResponse;
import rx.chris.thridparty.application.port.in.AnalyzeUseCase;

@RestController
@RequestMapping("/api/v1/analyze")
@RequiredArgsConstructor
public class AntiFraudController {

    private final AnalyzeUseCase analyzeUseCase;

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<AnalyzeResponse> analyzeEndpoint(@PathVariable("accountId") String accountId) {
        return analyzeUseCase.analyzeService(accountId);
    }
}

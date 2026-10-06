package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.PricingUseCase;
import rx.chris.thridparty.domain.Pricing;

@RestController
@RequestMapping("/api/pricing")
@RequiredArgsConstructor
public class QuoterController {

    private final PricingUseCase pricingUseCase;

    @GetMapping
    @ResponseBody
    public Mono<Pricing> getQuotation(@RequestParam("weight") double weight, @RequestParam("zone") String zone) {
        return pricingUseCase.getQuotation(weight, zone);
    }
}

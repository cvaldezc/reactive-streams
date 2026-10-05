package rx.chris.processcheckout.expose.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rx.chris.processcheckout.model.CheckoutRequest;
import rx.chris.processcheckout.model.CheckoutResponse;
import rx.chris.processcheckout.service.CheckoutProcessorService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutProcessorService checkoutProcessorService;

    @PostMapping("/checkout")
    Mono<CheckoutResponse> processCheckout(@RequestBody CheckoutRequest request) {
        return checkoutProcessorService.checkout(request);
    }
}

package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.BalanceUserCase;
import rx.chris.thridparty.domain.Balance;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LegacyController {

    private final BalanceUserCase balanceUserCase;

    @GetMapping("/balance/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Balance> getBalance(@PathVariable("userid") String userid) {
        return balanceUserCase.getBalance(userid);
    }
}

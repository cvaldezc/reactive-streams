package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.InventoryUseCase;
import rx.chris.thridparty.infrastructure.in.rest.dto.InventoryResponse;
import rx.chris.thridparty.infrastructure.in.rest.dto.ReserveRequest;
import rx.chris.thridparty.domain.Reserve;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryUseCase inventoryUseCase;

    @GetMapping("/v1/inventory/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<InventoryResponse> getAvailableProduct(@PathVariable("productId") String productId) {
        return inventoryUseCase.getInventory(productId);
    }

    @PostMapping("/inventory/reserve")
    Mono<Reserve> doReserved(@RequestBody ReserveRequest request) {
        return inventoryUseCase.doReserve(request);
    }
}

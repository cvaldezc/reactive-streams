package rx.chris.thridparty.infrastructure.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.thridparty.application.port.in.InventoryUseCase;
import rx.chris.thridparty.infrastructure.in.rest.dto.InventoryResponse;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryUseCase inventoryUseCase;

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<InventoryResponse> getAvailableProduct(@PathVariable("productId") String productId) {
        return inventoryUseCase.getInventory(productId);
    }
}

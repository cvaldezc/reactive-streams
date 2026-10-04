package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.infrastructure.in.rest.dto.InventoryResponse;

public interface InventoryUseCase {

    Mono<InventoryResponse> getInventory(String productId);
}

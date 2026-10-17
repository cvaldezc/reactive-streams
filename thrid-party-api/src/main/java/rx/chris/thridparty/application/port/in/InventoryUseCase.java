package rx.chris.thridparty.application.port.in;

import reactor.core.publisher.Mono;
import rx.chris.thridparty.domain.Reserve;
import rx.chris.thridparty.infrastructure.in.rest.dto.InventoryResponse;
import rx.chris.thridparty.infrastructure.in.rest.dto.ReserveRequest;

public interface InventoryUseCase {

    Mono<InventoryResponse> getInventory(String productId);

    Mono<Reserve> doReserve(ReserveRequest request);
}

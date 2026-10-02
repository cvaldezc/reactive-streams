package rx.chris.orderrequest.infra.adatper.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rx.chris.orderrequest.domain.Order;
import rx.chris.orderrequest.infra.adatper.persistence.entity.OrderEntity;
import rx.chris.orderrequest.infra.adatper.web.dto.OrderRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderEntity toOrderEntity(Order order);
    Order toOrder(OrderEntity order);
}

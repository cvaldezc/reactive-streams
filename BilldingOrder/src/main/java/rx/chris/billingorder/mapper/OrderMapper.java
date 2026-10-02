package rx.chris.billingorder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rx.chris.billingorder.model.Order;
import rx.chris.billingorder.repository.OrderEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order toMapper(OrderEntity entity);
}

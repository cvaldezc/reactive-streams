package rx.chris.shippingrouter.repository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import rx.chris.shippingrouter.model.RouteResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShipmentMapper {

    @Mapping(source = "finalCost", target = "cost")
    RouteResponse toResponse(ShipmentEntity entity);
}

package rx.chris.processcheckout.repository;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rx.chris.processcheckout.model.CheckoutResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CheckoutMapper {

    CheckoutResponse toResponse(CheckoutEntity entity);
}

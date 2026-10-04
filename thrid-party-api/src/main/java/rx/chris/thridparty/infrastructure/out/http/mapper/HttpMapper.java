package rx.chris.thridparty.infrastructure.out.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rx.chris.thridparty.domain.ExchangeRate;
import rx.chris.thridparty.infrastructure.out.http.dto.ExchangeResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HttpMapper {

    ExchangeRate toExchangeRate(ExchangeResponse response);
}

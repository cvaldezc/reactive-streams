package rx.chris.ratesexchange.rates.infraestructure.out.http.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rx.chris.ratesexchange.rates.domain.ExchangeRate;
import rx.chris.ratesexchange.rates.infraestructure.out.http.dto.ExchangeResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HttpMapper {

    ExchangeRate toExchangeRate(ExchangeResponse response);
}

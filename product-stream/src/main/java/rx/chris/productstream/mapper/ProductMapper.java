package rx.chris.productstream.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import rx.chris.productstream.adapter.entity.ProductEntity;
import rx.chris.productstream.controller.dto.ProductRequestDto;
import rx.chris.productstream.controller.dto.ProductResponseDto;
import rx.chris.productstream.model.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponseDto toProductResponseDto(Product product);
    Product toProduct(ProductRequestDto requestDto);

    @Mapping(target = "id", ignore = true)
    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}

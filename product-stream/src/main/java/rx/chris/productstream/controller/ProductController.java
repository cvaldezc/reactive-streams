package rx.chris.productstream.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rx.chris.productstream.controller.dto.ProductRequestDto;
import rx.chris.productstream.controller.dto.ProductResponseDto;
import rx.chris.productstream.mapper.ProductMapper;
import rx.chris.productstream.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto) {
        return productService.saveProduct(productMapper.toProduct(dto)).map(productMapper::toProductResponseDto);
    }

    @PostMapping("/hasCategory")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ProductResponseDto> createProductWithValidCategory(@RequestBody ProductRequestDto dto) {
        return productService.saveWithCategory(productMapper.toProduct(dto)).map(productMapper::toProductResponseDto);
    }

}

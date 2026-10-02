package rx.chris.productstream.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import rx.chris.productstream.adapter.httpclient.CategoryAdapter;
import rx.chris.productstream.adapter.httpclient.ExchangeRateClient;
import rx.chris.productstream.adapter.repository.ProductRepository;
import rx.chris.productstream.mapper.ProductMapper;
import rx.chris.productstream.model.Product;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ExchangeRateClient exchangeRateClient;
    private final CategoryAdapter categoryAdapter;

    private static final String CURRENCY_USD = "USD";
    private static final String CURRENCY_PEN = "PEN";

    @Override
    public Mono<Product> saveProduct(Product product) {
        return exchangeRateClient.getExchangeRate(CURRENCY_USD)
                .map(exchangeRate -> {
                    var rate = exchangeRate.rates().getOrDefault(CURRENCY_PEN, BigDecimal.ZERO);
                    var price_pen = product.priceUsd().multiply(rate);
                    return new Product(product.id(), product.name(), product.priceUsd(), price_pen);
                })
                .flatMap(prod -> productRepository.save(productMapper.toProductEntity(prod)))
                .map(productMapper::toProduct)
                .doOnError(e -> log.error("Fallo al procesar el producto {}: {}", product.name(), e.getMessage()))
                .onErrorMap(WebClientResponseException.class, e -> new RuntimeException("Servicio de tipos de cambio no disponible"));
    }

    @Override
    public Mono<Product> saveWithCategory(Product product) {
        return Mono.zip(exchangeRateClient.getRateFrom(CURRENCY_USD, CURRENCY_PEN), categoryAdapter.hasCategoryActive(product))
                .filter(Tuple2::getT2)
                .switchIfEmpty(Mono.error(() -> new RuntimeException("El producto no tiene categoria")))
                .map(tuple -> {
                    var rate = tuple.getT1();
                    var pricePen = product.priceUsd().multiply(rate);
                    return new Product(product.id(), product.name(), product.priceUsd(), pricePen, rate, true);
                })
                .flatMap(prod -> productRepository.save(productMapper.toProductEntity(prod)))
                .map(productMapper::toProduct)
                .doOnError(e -> log.error("Fallo al procesar el producto {}: {}", product.name(), e.getMessage()))
                .onErrorMap(WebClientResponseException.class, e -> new RuntimeException("Fallo al procesar el producto"));
    }
}

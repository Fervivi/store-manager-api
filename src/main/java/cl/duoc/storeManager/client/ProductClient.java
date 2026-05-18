/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.config.WebClientConfigStoreManager;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final WebClientConfigStoreManager productWebClient;

    public List<ProductResponseDto> getAllProducts() {
        ProductResponseDto[] products = productWebClient
                .get()
                .uri("/api/v1/products")
                .retrieve()
                .bodyToMono(ProductResponseDto[].class)
                .block();

        return Arrays.asList(products);
    }

    public ProductResponseDto getProductById(Long id) {
        return productWebClient
                .get()
                .uri("/api/v1/products/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.error(new ResourceNotFoundException("Producto no encontrado con id: " + id)))
                .bodyToMono(ProductResponseDto.class)
                .block();
    }
}

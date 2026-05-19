/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final WebClient webClientProduct;

    public ProductResponseDto getProductById(Long productId) {
        return webClientProduct
                .get()
                .uri("/api/v1/products/{id}", productId)
                .retrieve()
                .bodyToMono(ProductResponseDto.class)
                .block();
    }
}

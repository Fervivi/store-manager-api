/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductClient {
    private final WebClient webClientProduct;

    private static final String prefix = "/api/v1/products";

    public ProductResponseDto getProductById(Long productId, String token) {
        return webClientProduct
                .get()
                .uri(prefix + "/{id}", productId)
                .header("Authorization", token)
                .retrieve()
                .onStatus(
                        status -> status.value() == HttpStatus.NOT_FOUND.value(),
                        response -> Mono.error(
                                new ResourceNotFoundException("Producto no encontrado con ID: " + productId)))
                .bodyToMono(ProductResponseDto.class)
                .block();
    }
}

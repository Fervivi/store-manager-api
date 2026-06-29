/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.service;

import cl.duoc.storeManager.client.ProductClient;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreManagerService {

    private final ProductClient productClient;

    public ProductResponseDto validateProduct(Long productId, String token) {
        ProductResponseDto product = productClient.getProductById(productId, token);

        if (product == null) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + productId);
        }

        if (!Boolean.TRUE.equals(product.getActivo())) {
            throw new IllegalArgumentException("El producto no está activo");
        }

        if (product.getStock() == null || product.getStock() <= 0) {
            throw new IllegalArgumentException("El producto no tiene stock disponible");
        }

        return product;
    }
}

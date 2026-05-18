/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.service;

import cl.duoc.storeManager.client.ProductClient;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreManagerService {
    private final ProductClient productClient;

    public List<ProductResponseDto> getAllProducts() {
        return productClient.getAllProducts();
    }

    public ProductResponseDto getProductById(Long id) {
        return productClient.getProductById(id);
    }
}

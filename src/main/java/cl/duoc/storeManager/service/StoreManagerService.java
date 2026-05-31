/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.service;

import cl.duoc.storeManager.client.CartClient;
import cl.duoc.storeManager.client.ProductClient;
import cl.duoc.storeManager.dto.request.CartCreationRequest;
import cl.duoc.storeManager.dto.request.CartUpdateRequest;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreManagerService {

    private final ProductClient productClient;
    private final CartClient cartClient;

    public CartResponseDto createCart(CartCreationRequest request, String token) {
        return cartClient.createCart(request, token);
    }

    public CartResponseDto getCartById(Long cartId, String token) {
        return cartClient.getCartById(cartId, token);
    }

    public List<CartResponseDto> getAllCarts(String token) {
        return cartClient.getAllCarts(token);
    }

    public CartResponseDto updateCart(Long cartId, CartUpdateRequest request, String token) {
        return cartClient.updateCart(cartId, request, token);
    }

    public void deleteCart(Long cartId, String token) {
        cartClient.deleteCart(cartId, token);
    }

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

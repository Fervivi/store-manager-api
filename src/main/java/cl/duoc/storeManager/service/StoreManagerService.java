/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.service;

import cl.duoc.storeManager.client.CartClient;
import cl.duoc.storeManager.client.ProductClient;
import cl.duoc.storeManager.dto.request.AddProductToCartRequestDto;
import cl.duoc.storeManager.dto.request.CartItemRequestDto;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.dto.response.StoreCartResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreManagerService {

    private final ProductClient productClient;
    private final CartClient cartClient;

    public CartResponseDto addProductToCart(AddProductToCartRequestDto request) {

        ProductResponseDto product = productClient.getProductById(request.getProductId());

        if (product == null) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + request.getProductId());
        }

        if (!Boolean.TRUE.equals(product.getActivo())) {
            throw new IllegalArgumentException("El producto no está activo");
        }

        if (product.getStock() < request.getCantidad()) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getNombre());
        }

        CartItemRequestDto cartRequest = new CartItemRequestDto(
                request.getProductId(),
                request.getCantidad(),
                product.getPrecio().intValue());

        return cartClient.addItemToCart(request.getCartId(), cartRequest);
    }

    public List<StoreCartResponseDto> getCartWithProductDetails(Long cartId) {

        CartResponseDto cart = cartClient.getCartById(cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Carrito no encontrado con ID: " + cartId);
        }

        return cart.getItems().stream()
                .map(item -> {
                    ProductResponseDto product = productClient.getProductById(item.getProduct());

                    BigDecimal subtotal = product.getPrecio().multiply(BigDecimal.valueOf(item.getQuantity()));

                    return new StoreCartResponseDto(
                            product.getId(), product.getNombre(), item.getQuantity(), product.getPrecio(), subtotal);
                })
                .toList();
    }

    public CartResponseDto updateItemQuantity(Long cartId, Long itemId, Long productId, Integer cantidad) {

        ProductResponseDto product = productClient.getProductById(productId);

        if (product == null) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + productId);
        }

        if (!Boolean.TRUE.equals(product.getActivo())) {
            throw new IllegalArgumentException("El producto no está activo");
        }

        if (product.getStock() < cantidad) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getNombre());
        }

        CartItemRequestDto cartRequest =
                new CartItemRequestDto(productId, cantidad, product.getPrecio().intValue());

        return cartClient.updateItem(cartId, itemId, cartRequest);
    }

    public CartResponseDto removeItemFromCart(Long cartId, Long itemId) {
        return cartClient.removeItemFromCart(cartId, itemId);
    }
}

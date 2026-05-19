/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.dto.request.AddProductToCartRequestDto;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final WebClient webClientCart;

    public CartResponseDto addProductToCart(AddProductToCartRequestDto request) {
        return webClientCart
                .post()
                .uri("/api/v1/carts/{cartId}/products", request.getCartId())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto getCartById(Long cartId) {
        return webClientCart
                .get()
                .uri("/api/v1/carts/{cartId}", cartId)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto updateProductQuantity(Long cartId, Long productId, Integer cantidad) {
        return webClientCart
                .put()
                .uri("/api/v1/carts/{cartId}/products/{productId}?cantidad={cantidad}", cartId, productId, cantidad)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        webClientCart
                .delete()
                .uri("/api/v1/carts/{cartId}/products/{productId}", cartId, productId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}

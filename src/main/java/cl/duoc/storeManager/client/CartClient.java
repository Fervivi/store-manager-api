/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.dto.request.CartItemRequestDto;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final WebClient webClientCart;

    public CartResponseDto addItemToCart(Long cartId, CartItemRequestDto request) {
        return webClientCart
                .post()
                .uri("/api/v1/carts/{cartId}/items", cartId)
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

    public CartResponseDto updateItem(Long cartId, Long itemId, CartItemRequestDto request) {
        return webClientCart
                .put()
                .uri("/api/v1/carts/{cartId}/items/{itemId}", cartId, itemId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto removeItemFromCart(Long cartId, Long itemId) {
        return webClientCart
                .delete()
                .uri("/api/v1/carts/{cartId}/items/{itemId}", cartId, itemId)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }
}

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

    public CartResponseDto addItemToCart(Long cartId, CartItemRequestDto request, String token) {
        return webClientCart
                .post()
                .uri("/api/v1/carts/{cartId}/items", cartId)
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto getCartById(Long cartId, String token) {
        return webClientCart
                .get()
                .uri("/api/v1/carts/{cartId}", cartId)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto updateItem(Long cartId, Long itemId, CartItemRequestDto request, String token) {
        return webClientCart
                .put()
                .uri("/api/v1/carts/{cartId}/items/{itemId}", cartId, itemId)
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto removeItemFromCart(Long cartId, Long itemId, String token) {
        return webClientCart
                .delete()
                .uri("/api/v1/carts/{cartId}/items/{itemId}", cartId, itemId)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }
}

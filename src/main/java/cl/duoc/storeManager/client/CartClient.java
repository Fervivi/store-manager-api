/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.dto.response.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final WebClient webClientCart;

    public CartResponseDto createCart(CartCreationRequest request, String token) {
        return webClientCart
                .post()
                .uri("")
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto getCartById(Long cartId, String token) {
        return webClientCart
                .get()
                .uri("/{id}", cartId)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public List<CartResponseDto> getAllCarts(String token) {
        return webClientCart
                .get()
                .uri("")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CartResponseDto>>() {})
                .block();
    }

    public CartResponseDto updateCart(Long cartId, CartUpdateRequest request, String token) {
        return webClientCart
                .put()
                .uri("/{id}", cartId)
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public void deleteCart(Long cartId, String token) {
        webClientCart
                .delete()
                .uri("/{id}", cartId)
                .header("Authorization", token)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}

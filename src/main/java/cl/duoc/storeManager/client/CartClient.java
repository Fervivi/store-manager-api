/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import cl.duoc.storeManager.dto.request.CartCreationRequest;
import cl.duoc.storeManager.dto.request.CartUpdateRequest;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final WebClient webClientCart;

    private static final String prefix = "/api/v1/carts";

    public CartResponseDto createCart(CartCreationRequest request, String token) {
        return webClientCart
                .post()
                .uri(prefix)
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public CartResponseDto getCartById(Long cartId, String token) {
        return webClientCart
                .get()
                .uri(prefix + "/{id}", cartId)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public List<CartResponseDto> getAllCarts(String token) {
        return webClientCart
                .get()
                .uri(prefix)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CartResponseDto>>() {})
                .block();
    }

    public CartResponseDto updateCart(Long cartId, CartUpdateRequest request, String token) {
        return webClientCart
                .put()
                .uri(prefix + "/{id}", cartId)
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CartResponseDto.class)
                .block();
    }

    public void deleteCart(Long cartId, String token) {
        webClientCart
                .delete()
                .uri(prefix + "/{id}", cartId)
                .header("Authorization", token)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}

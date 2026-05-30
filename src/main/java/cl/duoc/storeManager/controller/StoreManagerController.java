/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.controller;

import cl.duoc.storeManager.dto.request.AddProductToCartRequestDto;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import cl.duoc.storeManager.dto.response.StoreCartResponseDto;
import cl.duoc.storeManager.service.StoreManagerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class StoreManagerController {
    private final StoreManagerService storeManagerService;

    @GetMapping("/health")
    public String health() {
        return "StoreManager funcionando correctamente";
    }

    @PostMapping("/cart/products")
    public CartResponseDto addProductToCart(
            @RequestHeader("Authorization") String token, @RequestBody AddProductToCartRequestDto request) {

        return storeManagerService.addProductToCart(request, token);
    }

    @GetMapping("/cart/{cartId}")
    public List<StoreCartResponseDto> getCartWithProductDetails(
            @RequestHeader("Authorization") String token, @PathVariable Long cartId) {

        return storeManagerService.getCartWithProductDetails(cartId, token);
    }

    @PutMapping("/cart/{cartId}/items/{itemId}")
    public CartResponseDto updateItemQuantity(
            @RequestHeader("Authorization") String token,
            @PathVariable Long cartId,
            @PathVariable Long itemId,
            @RequestParam Long productId,
            @RequestParam Integer cantidad) {

        return storeManagerService.updateItemQuantity(cartId, itemId, productId, cantidad, token);
    }

    @DeleteMapping("/cart/{cartId}/items/{itemId}")
    public CartResponseDto removeItemFromCart(
            @RequestHeader("Authorization") String token, @PathVariable Long cartId, @PathVariable Long itemId) {

        return storeManagerService.removeItemFromCart(cartId, itemId, token);
    }
}

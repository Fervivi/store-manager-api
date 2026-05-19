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
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreManagerController {
    private final StoreManagerService storeManagerService;

    @PostMapping("/cart/products")
    public CartResponseDto addProductToCart(@RequestBody AddProductToCartRequestDto request) {
        return storeManagerService.addProductToCart(request);
    }

    @GetMapping("/cart/{cartId}")
    public List<StoreCartResponseDto> getCartWithProductDetails(@PathVariable Long cartId) {
        return storeManagerService.getCartWithProductDetails(cartId);
    }

    @PutMapping("/cart/{cartId}/products/{productId}")
    public CartResponseDto updateProductQuantity(
            @PathVariable Long cartId, @PathVariable Long productId, @RequestParam Integer cantidad) {
        return storeManagerService.updateProductQuantity(cartId, productId, cantidad);
    }

    @DeleteMapping("/cart/{cartId}/products/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        storeManagerService.removeProductFromCart(cartId, productId);
    }
}

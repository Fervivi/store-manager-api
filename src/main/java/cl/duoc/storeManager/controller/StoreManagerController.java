/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.controller;

import cl.duoc.storeManager.dto.request.CartCreationRequest;
import cl.duoc.storeManager.dto.request.CartUpdateRequest;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
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

    @PostMapping("/carts")
    public CartResponseDto createCart(
            @RequestHeader("Authorization") String token, @RequestBody CartCreationRequest request) {

        return storeManagerService.createCart(request, token);
    }

    @GetMapping("/carts/{cartId}")
    public CartResponseDto getCartById(@RequestHeader("Authorization") String token, @PathVariable Long cartId) {

        return storeManagerService.getCartById(cartId, token);
    }

    @GetMapping("/carts")
    public List<CartResponseDto> getAllCarts(@RequestHeader("Authorization") String token) {

        return storeManagerService.getAllCarts(token);
    }

    @PutMapping("/carts/{cartId}")
    public CartResponseDto updateCart(
            @RequestHeader("Authorization") String token,
            @PathVariable Long cartId,
            @RequestBody CartUpdateRequest request) {

        return storeManagerService.updateCart(cartId, request, token);
    }

    @DeleteMapping("/carts/{cartId}")
    public void deleteCart(@RequestHeader("Authorization") String token, @PathVariable Long cartId) {

        storeManagerService.deleteCart(cartId, token);
    }

    @GetMapping("/products/{productId}/validate")
    public ProductResponseDto validateProduct(
            @RequestHeader("Authorization") String token, @PathVariable Long productId) {

        return storeManagerService.validateProduct(productId, token);
    }
}

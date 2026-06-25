/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.duoc.storeManager.client.CartClient;
import cl.duoc.storeManager.client.ProductClient;
import cl.duoc.storeManager.dto.request.CartCreationRequest;
import cl.duoc.storeManager.dto.response.CartResponseDto;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreManagerServiceTest {

    private static final String TOKEN = "Bearer token-prueba";

    @Mock
    private ProductClient productClient;

    @Mock
    private CartClient cartClient;

    @InjectMocks
    private StoreManagerService storeManagerService;

    @Test
    void createCart_debeDelegarCreacionAlCartClient() {
        // Given
        CartCreationRequest request = new CartCreationRequest(10L);
        CartResponseDto expected = crearCarrito();
        when(cartClient.createCart(request, TOKEN)).thenReturn(expected);

        // When
        CartResponseDto result = storeManagerService.createCart(request, TOKEN);

        // Then
        assertThat(result).isSameAs(expected);
        verify(cartClient).createCart(request, TOKEN);
    }

    @Test
    void getAllCarts_debeRetornarListaEntregadaPorCartClient() {
        // Given
        List<CartResponseDto> expected = List.of(crearCarrito());
        when(cartClient.getAllCarts(TOKEN)).thenReturn(expected);

        // When
        List<CartResponseDto> result = storeManagerService.getAllCarts(TOKEN);

        // Then
        assertThat(result).containsExactlyElementsOf(expected);
        verify(cartClient).getAllCarts(TOKEN);
    }

    @Test
    void validateProduct_debeRetornarProductoCuandoEstaActivoYConStock() {
        // Given
        ProductResponseDto expected = crearProducto(5, true);
        when(productClient.getProductById(1L, TOKEN)).thenReturn(expected);

        // When
        ProductResponseDto result = storeManagerService.validateProduct(1L, TOKEN);

        // Then
        assertThat(result).isSameAs(expected);
        verify(productClient).getProductById(1L, TOKEN);
    }

    @Test
    void validateProduct_debeLanzarExcepcionCuandoProductoNoExiste() {
        // Given
        when(productClient.getProductById(99L, TOKEN)).thenReturn(null);

        // When / Then
        assertThatThrownBy(() -> storeManagerService.validateProduct(99L, TOKEN))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Producto no encontrado con ID: 99");
    }

    @Test
    void validateProduct_debeLanzarExcepcionCuandoProductoNoTieneStock() {
        // Given
        when(productClient.getProductById(1L, TOKEN)).thenReturn(crearProducto(0, true));

        // When / Then
        assertThatThrownBy(() -> storeManagerService.validateProduct(1L, TOKEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El producto no tiene stock disponible");
    }

    private CartResponseDto crearCarrito() {
        LocalDateTime now = LocalDateTime.of(2026, 6, 25, 12, 0);
        return new CartResponseDto(1L, now, now, null);
    }

    private ProductResponseDto crearProducto(Integer stock, Boolean activo) {
        return new ProductResponseDto(
                1L, "Teclado", "Teclado mecanico", new BigDecimal("29990"), stock, "Perifericos", activo);
    }
}

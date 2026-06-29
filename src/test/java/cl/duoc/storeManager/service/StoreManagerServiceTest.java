/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import cl.duoc.storeManager.client.ProductClient;
import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import java.math.BigDecimal;
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

    @InjectMocks
    private StoreManagerService storeManagerService;

    @Test
    void validateProduct_debeRetornarProductoCuandoEstaActivoYConStock() {
        ProductResponseDto expected = crearProducto(5, true);
        when(productClient.getProductById(1L, TOKEN)).thenReturn(expected);

        ProductResponseDto result = storeManagerService.validateProduct(1L, TOKEN);

        assertThat(result).isSameAs(expected);
    }

    @Test
    void validateProduct_debeLanzarExcepcionCuandoProductoNoExiste() {
        when(productClient.getProductById(99L, TOKEN)).thenReturn(null);

        assertThatThrownBy(() -> storeManagerService.validateProduct(99L, TOKEN))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Producto no encontrado con ID: 99");
    }

    @Test
    void validateProduct_debeLanzarExcepcionCuandoProductoNoTieneStock() {
        when(productClient.getProductById(1L, TOKEN)).thenReturn(crearProducto(0, true));

        assertThatThrownBy(() -> storeManagerService.validateProduct(1L, TOKEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El producto no tiene stock disponible");
    }

    private ProductResponseDto crearProducto(Integer stock, Boolean activo) {
        return new ProductResponseDto(
                1L, "Teclado", "Teclado mecanico", new BigDecimal("29990"), stock, "Perifericos", activo);
    }
}

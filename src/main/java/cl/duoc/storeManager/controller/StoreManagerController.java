/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.controller;

import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.service.StoreManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store-manager")
@RequiredArgsConstructor
@Tag(name = "Store Manager", description = "Operaciones de tienda, carritos y validacion de productos")
@SecurityRequirement(name = "bearerAuth")
public class StoreManagerController {
    private final StoreManagerService storeManagerService;

    @GetMapping("/health")
    @Operation(
            summary = "Verifica el estado del microservicio",
            description = "Endpoint publico para comprobar que Store Manager esta disponible.")
    @ApiResponse(responseCode = "200", description = "Microservicio disponible")
    public String health() {
        return "StoreManager funcionando correctamente";
    }

    @GetMapping("/products/{productId}/validate")
    @Operation(
            summary = "Valida un producto",
            description =
                    "Consulta el microservicio de productos y valida que el producto exista, este activo y tenga stock disponible.")
    @ApiResponse(
            responseCode = "200",
            description = "Producto valido",
            content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Producto inactivo o sin stock",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "401", description = "Token JWT ausente o invalido")
    @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del microservicio",
            content = @Content(schema = @Schema(implementation = String.class)))
    public ProductResponseDto validateProduct(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token,
            @Parameter(description = "ID del producto", example = "1") @PathVariable Long productId) {

        return storeManagerService.validateProduct(productId, token);
    }
}

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @PostMapping("/carts")
    @Operation(
            summary = "Crea un carrito",
            description = "Orquesta la creacion de un carrito en el microservicio de carritos.")
    @ApiResponse(
            responseCode = "200",
            description = "Carrito creado correctamente",
            content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CartResponseDto.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Solicitud invalida",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "401", description = "Token JWT ausente o invalido")
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del microservicio",
            content = @Content(schema = @Schema(implementation = String.class)))
    public CartResponseDto createCart(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token,
            @Valid @RequestBody CartCreationRequest request) {

        return storeManagerService.createCart(request, token);
    }

    @GetMapping("/carts/{cartId}")
    @Operation(
            summary = "Obtiene un carrito por ID",
            description = "Consulta un carrito existente desde el microservicio de carritos.")
    @ApiResponse(
            responseCode = "200",
            description = "Carrito encontrado",
            content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CartResponseDto.class)))
    @ApiResponse(responseCode = "401", description = "Token JWT ausente o invalido")
    @ApiResponse(
            responseCode = "404",
            description = "Carrito no encontrado",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del microservicio",
            content = @Content(schema = @Schema(implementation = String.class)))
    public CartResponseDto getCartById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token,
            @Parameter(description = "ID del carrito", example = "1") @PathVariable Long cartId) {

        return storeManagerService.getCartById(cartId, token);
    }

    @GetMapping("/carts")
    @Operation(
            summary = "Lista carritos",
            description = "Obtiene todos los carritos registrados en el microservicio de carritos.")
    @ApiResponse(
            responseCode = "200",
            description = "Listado de carritos",
            content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CartResponseDto.class))))
    @ApiResponse(responseCode = "401", description = "Token JWT ausente o invalido")
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del microservicio",
            content = @Content(schema = @Schema(implementation = String.class)))
    public List<CartResponseDto> getAllCarts(@Parameter(hidden = true) @RequestHeader("Authorization") String token) {

        return storeManagerService.getAllCarts(token);
    }

    @PutMapping("/carts/{cartId}")
    @Operation(summary = "Actualiza un carrito", description = "Orquesta la actualizacion de un carrito existente.")
    @ApiResponse(
            responseCode = "200",
            description = "Carrito actualizado correctamente",
            content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CartResponseDto.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Solicitud invalida",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "401", description = "Token JWT ausente o invalido")
    @ApiResponse(
            responseCode = "404",
            description = "Carrito no encontrado",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del microservicio",
            content = @Content(schema = @Schema(implementation = String.class)))
    public CartResponseDto updateCart(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token,
            @Parameter(description = "ID del carrito", example = "1") @PathVariable Long cartId,
            @Valid @RequestBody CartUpdateRequest request) {

        return storeManagerService.updateCart(cartId, request, token);
    }

    @DeleteMapping("/carts/{cartId}")
    @Operation(
            summary = "Elimina un carrito",
            description = "Solicita la eliminacion de un carrito al microservicio de carritos.")
    @ApiResponse(responseCode = "200", description = "Carrito eliminado correctamente")
    @ApiResponse(responseCode = "401", description = "Token JWT ausente o invalido")
    @ApiResponse(
            responseCode = "404",
            description = "Carrito no encontrado",
            content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(
            responseCode = "500",
            description = "Error interno del microservicio",
            content = @Content(schema = @Schema(implementation = String.class)))
    public void deleteCart(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token,
            @Parameter(description = "ID del carrito", example = "1") @PathVariable Long cartId) {

        storeManagerService.deleteCart(cartId, token);
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

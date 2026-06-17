/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta con informacion de un producto validado")
public class ProductResponseDto {

    @Schema(description = "ID del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Teclado mecanico")
    private String nombre;

    @Schema(description = "Descripcion del producto", example = "Teclado mecanico compacto con switches tactiles")
    private String descripcion;

    @Schema(description = "Precio unitario del producto", example = "29990")
    private BigDecimal precio;

    @Schema(description = "Stock disponible", example = "15")
    private Integer stock;

    @Schema(description = "Categoria del producto", example = "Perifericos")
    private String categoria;

    @Schema(description = "Indica si el producto esta activo", example = "true")
    private Boolean activo;
}

/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con informacion de un carrito")
public class CartResponseDto {

    @Schema(description = "ID del carrito", example = "1")
    private Long id;

    @Schema(description = "Fecha de creacion del carrito", example = "2026-06-16T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de ultima actualizacion del carrito", example = "2026-06-16T11:20:10")
    private LocalDateTime updatedAt;

    @Schema(description = "Fecha de eliminacion logica del carrito", example = "2026-06-16T12:00:00", nullable = true)
    private LocalDateTime deletedAt;
}

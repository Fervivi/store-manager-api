/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos necesarios para actualizar un carrito")
public class CartUpdateRequest {
    @NotNull(message = "El id es obligatorio")
    @Positive(message = "El id debe ser positivo")
    @Schema(
            description = "ID del cliente propietario del carrito",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Long customerId;
}

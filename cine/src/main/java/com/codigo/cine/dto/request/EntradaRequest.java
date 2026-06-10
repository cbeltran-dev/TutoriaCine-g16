package com.codigo.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaRequest {

    @NotNull(message = "La funcion es obligatoria")
    private Long funcionId;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull( message = "La cantidad es obligatoria")
    @Positive(message = "La cantiodad debe ser mayor a 0")
    private Integer cantidad;
}

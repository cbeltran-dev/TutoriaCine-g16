package com.codigo.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaRequest {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;
    @NotBlank(message = "El genero es obligatorio")
    private  String genero;

    @NotNull(message = "La duracion es obligatoria")
    @Positive(message = "La duracion debe ser mayor a 0")
    private Integer duracionMinutos;
}

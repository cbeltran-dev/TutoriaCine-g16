package com.codigo.cine.dto.request;

import com.codigo.cine.entity.TipoFuncion;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FuncionRequest {

    @NotNull(message = "La pelicula es obligatoria")
    private Long peliculaId;

    @NotNull(message = "El tipo funcion es obligatorio")
    private TipoFuncion tipoFuncion;

    @NotNull(message = "La fecha y hora son obligatorios")
    @Future(message = "La fecha debe ser en el futuro")
    private LocalDateTime fechaHora;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precioBase;

    @NotNull(message = "Los asientos son obligatorios")
    @Positive(message = "Los asientos deben ser mayor a 0")
    private Integer asientosDisponibles;
}

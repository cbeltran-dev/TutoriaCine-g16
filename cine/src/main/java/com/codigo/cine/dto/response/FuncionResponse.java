package com.codigo.cine.dto.response;

import com.codigo.cine.entity.Pelicula;
import com.codigo.cine.entity.TipoFuncion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FuncionResponse {

    private Long id;
    private TipoFuncion tipo;
    private LocalDateTime fechaHora;
    private Double precioBase;
    private Integer asientosDisponibles;



}

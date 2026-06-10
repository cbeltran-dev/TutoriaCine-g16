package com.codigo.cine.dto.response;

import com.codigo.cine.entity.Funcion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntradaResponse {

    private Long id;
    private String nombreCliente;
    private Integer cantidad;
    private Double precioUnitario;
    private Double precioTotal;
    private String promocionAplicada;
    private LocalDateTime fechaCompra;
    private String tituloPelicula;

}

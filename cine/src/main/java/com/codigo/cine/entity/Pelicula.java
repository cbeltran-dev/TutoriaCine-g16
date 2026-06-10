package com.codigo.cine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 150)
    private String titulo;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos;

    @OneToMany(mappedBy = "pelicula", fetch = FetchType.LAZY)
    private List<Funcion> funciones = new ArrayList<>();

}

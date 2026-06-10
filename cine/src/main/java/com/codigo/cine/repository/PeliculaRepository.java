package com.codigo.cine.repository;

import com.codigo.cine.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

//    @Query(value = "SELECT * FROM peliculas WHERE LOWER(genero) = LOWER(:genero)", nativeQuery = true)
//    List<Pelicula> buscarPorGenero(String genero);

    List<Pelicula> findByGenero(String genero);

}

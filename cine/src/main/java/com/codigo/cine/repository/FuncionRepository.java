package com.codigo.cine.repository;

import com.codigo.cine.entity.Funcion;
import com.codigo.cine.entity.TipoFuncion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    List<Funcion>findByTipo(TipoFuncion tipo);

//    @Query("SELECT f FROM Funcion f JOIN FETCH f. WHERE f.fechaHora > :ahora ORDER BY f.fechaHora")
//    List<Funcion> buscarCartelera(@Param("ahora") LocalDateTime ahora);

}

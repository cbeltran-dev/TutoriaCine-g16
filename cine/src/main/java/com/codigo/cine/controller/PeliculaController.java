package com.codigo.cine.controller;

import com.codigo.cine.dto.request.PeliculaRequest;
import com.codigo.cine.dto.response.ApiResponse;
import com.codigo.cine.dto.response.PeliculaResponse;
import com.codigo.cine.service.PeliculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<ApiResponse<PeliculaResponse>> crear(
           @Valid @RequestBody PeliculaRequest request){
        PeliculaResponse response = peliculaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.exito("Pelicula Creada",response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PeliculaResponse>>> listar(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.exito("Peliculas encontradas", peliculaService.listar()));
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<ApiResponse<List<PeliculaResponse>>> listarPorGenero(
            @PathVariable String genero){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.exito("Busqueda exitosa", peliculaService.buscarPorGenero(genero)));
    }

}

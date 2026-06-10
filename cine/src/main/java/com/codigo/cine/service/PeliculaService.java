package com.codigo.cine.service;

import com.codigo.cine.dto.request.PeliculaRequest;
import com.codigo.cine.dto.response.PeliculaResponse;
import com.codigo.cine.entity.Pelicula;
import com.codigo.cine.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PeliculaResponse crear(PeliculaRequest request){

        Pelicula pelicula = new Pelicula();
        modelMapper.map(request,pelicula);

        Pelicula guardada = peliculaRepository.save(pelicula);

        PeliculaResponse response = modelMapper.map(guardada, PeliculaResponse.class);

        return response;
    }

    @Transactional(readOnly = true)
    public List<PeliculaResponse> listar(){
        return peliculaRepository.findAll()
                .stream()
                .map(pelicula -> modelMapper.map(pelicula, PeliculaResponse.class))
                .toList();
    }

    public List<PeliculaResponse> buscarPorGenero(String genero){

        return peliculaRepository.findByGenero(genero)
                .stream()
                .map(pelicula -> modelMapper.map(pelicula, PeliculaResponse.class))
                .toList();
    }

}

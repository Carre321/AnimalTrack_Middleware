package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;

public interface UsuarioLoginService {

    UsuarioLoginDTO findById(Long id);

    UsuarioLoginDTO findByEmail(String email);

    List<UsuarioLoginDTO> findAll();

    UsuarioLoginDTO create(UsuarioLoginDTO usuario);

    void update(UsuarioLoginDTO usuario);

    void delete(Long id);
}

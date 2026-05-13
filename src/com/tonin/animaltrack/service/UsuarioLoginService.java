package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;

public interface UsuarioLoginService {

    UsuarioLoginDTO findById(Long id) throws Exception;

    UsuarioLoginDTO findByEmail(String email) throws Exception;

    List<UsuarioLoginDTO> findAll() throws Exception;

    UsuarioLoginDTO create(UsuarioLoginDTO usuario) throws Exception;

    void update(UsuarioLoginDTO usuario) throws Exception;

    void delete(Long id) throws Exception;
}

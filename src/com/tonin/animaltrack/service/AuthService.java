package com.tonin.animaltrack.service;

import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;

public interface AuthService {

    UsuarioLoginDTO login(String email, String password) throws Exception;

    UsuarioLoginDTO findByEmail(String email) throws Exception;
}

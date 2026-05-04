package com.tonin.animaltrack.service;

import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;

public interface AuthService {

    UsuarioLoginDTO login(String email, String password);

    UsuarioLoginDTO findByEmail(String email);
}

package com.tonin.animaltrack.service.impl;

import com.tonin.animaltrack.dao.UsuarioLoginDAO;
import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;
import com.tonin.animaltrack.service.AuthService;
import com.tonin.animaltrack.service.EncryptionService;

public class AuthServiceImpl implements AuthService {

    private UsuarioLoginDAO usuarioLoginDAO = null;
    private EncryptionService encryptionService = null;

    public AuthServiceImpl() {
        this.usuarioLoginDAO = new UsuarioLoginDAO();
        this.encryptionService = new EncryptionServiceBCryptImpl();
    }

    @Override
    public UsuarioLoginDTO login(String email, String password) {
        String safeEmail = trimToNull(email);
        if (safeEmail == null || password == null || password.isEmpty()) {
            return null;
        }

        UsuarioLoginDTO usuario = usuarioLoginDAO.findByEmail(safeEmail);
        if (usuario == null || !Boolean.TRUE.equals(usuario.getActivo())) {
            return null;
        }

        return encryptionService.checkEncryption(password, usuario.getPasswordHash()) ? usuario : null;
    }

    @Override
    public UsuarioLoginDTO findByEmail(String email) {
        String safeEmail = trimToNull(email);
        return safeEmail == null ? null : usuarioLoginDAO.findByEmail(safeEmail);
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}

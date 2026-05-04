package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.UsuarioLoginDAO;
import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;
import com.tonin.animaltrack.service.EncryptionService;
import com.tonin.animaltrack.service.UsuarioLoginService;

public class UsuarioLoginServiceImpl implements UsuarioLoginService {

    private UsuarioLoginDAO usuarioLoginDAO;
    private EncryptionService encryptionService;

    public UsuarioLoginServiceImpl() {
        this.usuarioLoginDAO = new UsuarioLoginDAO();
        this.encryptionService = new EncryptionServiceBCryptImpl();
    }

    @Override
    public UsuarioLoginDTO findById(Long id) {
        return usuarioLoginDAO.findById(id);
    }

    @Override
    public UsuarioLoginDTO findByEmail(String email) {
        return usuarioLoginDAO.findByEmail(email);
    }

    @Override
    public List<UsuarioLoginDTO> findAll() {
        return usuarioLoginDAO.findAll();
    }

    @Override
    public UsuarioLoginDTO create(UsuarioLoginDTO usuario) {
        if (usuario == null || usuario.getEmail() == null || usuario.getPasswordHash() == null || usuario.getRol() == null) {
            return null;
        }
        usuario.setPasswordHash(normalizePassword(usuario.getPasswordHash()));
        Long id = usuarioLoginDAO.create(usuario);
        return id == null ? null : usuarioLoginDAO.findById(id);
    }

    @Override
    public void update(UsuarioLoginDTO usuario) {
        if (usuario != null && usuario.getId() != null) {
            usuario.setPasswordHash(normalizePassword(usuario.getPasswordHash()));
            usuarioLoginDAO.update(usuario);
        }
    }

    @Override
    public void delete(Long id) {
        usuarioLoginDAO.delete(id);
    }

    private String normalizePassword(String value) {
        if (value == null || value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$")) {
            return value;
        }
        return encryptionService.encrypt(value);
    }
}

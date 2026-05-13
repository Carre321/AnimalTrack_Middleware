package com.tonin.animaltrack.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.tonin.animaltrack.dao.UsuarioLoginDAO;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;
import com.tonin.animaltrack.service.AuthService;
import com.tonin.animaltrack.service.EncryptionService;

public class AuthServiceImpl implements AuthService {

    private static Logger logger = LogManager.getLogger(AuthServiceImpl.class.getName());

    private UsuarioLoginDAO usuarioLoginDAO = null;
    private EncryptionService encryptionService = null;

    public AuthServiceImpl() {
        this.usuarioLoginDAO = new UsuarioLoginDAO();
        this.encryptionService = new EncryptionServiceBCryptImpl();
    }

    @Override
    public UsuarioLoginDTO login(String email, String password) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            String safeEmail = trimToNull(email);
            if (safeEmail == null || password == null || password.isEmpty()) {
                commit = true;
                return null;
            }
            UsuarioLoginDTO usuario = usuarioLoginDAO.findByEmail(c, safeEmail);
            if (usuario == null || !Boolean.TRUE.equals(usuario.getActivo())) {
                commit = true;
                return null;
            }
            UsuarioLoginDTO result = encryptionService.checkEncryption(password, usuario.getPasswordHash()) ? usuario : null;
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public UsuarioLoginDTO findByEmail(String email) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            String safeEmail = trimToNull(email);
            UsuarioLoginDTO result = safeEmail == null ? null : usuarioLoginDAO.findByEmail(c, safeEmail);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}

package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.UsuarioLoginDAO;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;
import com.tonin.animaltrack.service.EncryptionService;
import com.tonin.animaltrack.service.UsuarioLoginService;

public class UsuarioLoginServiceImpl implements UsuarioLoginService {

    private static Logger logger = LogManager.getLogger(UsuarioLoginServiceImpl.class.getName());

    private UsuarioLoginDAO usuarioLoginDAO;
    private EncryptionService encryptionService;

    public UsuarioLoginServiceImpl() {
        this.usuarioLoginDAO = new UsuarioLoginDAO();
        this.encryptionService = new EncryptionServiceBCryptImpl();
    }

    @Override
    public UsuarioLoginDTO findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            UsuarioLoginDTO result = usuarioLoginDAO.findById(c, id);
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
            UsuarioLoginDTO result = usuarioLoginDAO.findByEmail(c, email);
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
    public List<UsuarioLoginDTO> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<UsuarioLoginDTO> result = usuarioLoginDAO.findAll(c);
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
    public UsuarioLoginDTO create(UsuarioLoginDTO usuario) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (usuario == null || usuario.getEmail() == null || usuario.getPasswordHash() == null || usuario.getRol() == null) {
                commit = true;
                return null;
            }
            usuario.setPasswordHash(normalizePassword(usuario.getPasswordHash()));
            Long id = usuarioLoginDAO.create(c, usuario);
            UsuarioLoginDTO result = id == null ? null : usuarioLoginDAO.findById(c, id);
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
    public void update(UsuarioLoginDTO usuario) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (usuario != null && usuario.getId() != null) {
                usuario.setPasswordHash(normalizePassword(usuario.getPasswordHash()));
                usuarioLoginDAO.update(c, usuario);
            }
            commit = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            usuarioLoginDAO.delete(c, id);
            commit = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    private String normalizePassword(String value) {
        if (value == null || value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$")) {
            return value;
        }
        return encryptionService.encrypt(value);
    }
}

package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.VeterinarioDAO;
import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;
import com.tonin.animaltrack.service.VeterinarioService;

public class VeterinarioServiceImpl implements VeterinarioService {

    private static Logger logger = LogManager.getLogger(VeterinarioServiceImpl.class.getName());

    private static final String REQUIRED_DATA_MESSAGE = "Faltan datos obligatorios. Revisa los datos introducidos.";

    private VeterinarioDAO veterinarioDAO = null;

    public VeterinarioServiceImpl() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    @Override
    public VeterinarioDTO findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            VeterinarioDTO result = veterinarioDAO.findById(c, id);
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
    public List<VeterinarioDTO> findByCriteria(VeterinarioCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<VeterinarioDTO> result = veterinarioDAO.findBy(c, criteria);
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
    public List<VeterinarioDTO> findByMunicipioId(Long municipioId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<VeterinarioDTO> result = veterinarioDAO.findByMunicipioId(c, municipioId);
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
    public List<VeterinarioDTO> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<VeterinarioDTO> result = veterinarioDAO.getAll(c);
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
    public VeterinarioDTO create(Veterinario veterinario) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            validateForSave(c, veterinario);
            Long id = veterinarioDAO.create(c, veterinario);
            VeterinarioDTO result = id == null ? null : veterinarioDAO.findById(c, id);
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
    public void update(Veterinario veterinario) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (veterinario != null && veterinario.getId() != null) {
                validateForSave(c, veterinario);
                veterinarioDAO.update(c, veterinario);
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
            veterinarioDAO.delete(c, id);
            commit = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    private void validateForSave(Connection c, Veterinario veterinario) throws Exception {
        if (veterinario == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (isBlank(veterinario.getCodigo())) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (isBlank(veterinario.getNombre())) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (veterinario.getMunicipioId() == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }

        String codigo = normalize(veterinario.getCodigo());
        veterinario.setCodigo(codigo);
        veterinario.setDireccion(normalize(veterinario.getDireccion()));
        veterinario.setCodigoPostal(normalize(veterinario.getCodigoPostal()));
        assertUniqueCodigo(c, veterinario, codigo);

        String dni = normalize(veterinario.getDni());
        veterinario.setDni(dni);
        if (dni != null) {
            assertUniqueDni(c, veterinario, dni);
        }
    }

    private void assertUniqueCodigo(Connection c, Veterinario veterinario, String codigo) throws Exception {
        VeterinarioCriteria criteria = new VeterinarioCriteria();
        criteria.setCodigo(codigo);
        assertNoOtherVeterinario(veterinario, veterinarioDAO.findBy(c, criteria));
    }

    private void assertUniqueDni(Connection c, Veterinario veterinario, String dni) throws Exception {
        VeterinarioCriteria criteria = new VeterinarioCriteria();
        criteria.setDni(dni);
        assertNoOtherVeterinario(veterinario, veterinarioDAO.findBy(c, criteria));
    }

    private void assertNoOtherVeterinario(Veterinario veterinario, List<VeterinarioDTO> matches) {
        if (matches == null) {
            throw new IllegalStateException("No se pudo comprobar si el veterinario ya existe.");
        }
        for (VeterinarioDTO existing : matches) {
            if (existing.getId() != null && !existing.getId().equals(veterinario.getId())) {
                throw new IllegalArgumentException("No se pudo guardar el veterinario. Revisa los datos introducidos.");
            }
        }
    }

    private String normalize(String value) {
        if (isBlank(value)) {
            return null;
        }
        return value.trim().toUpperCase();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

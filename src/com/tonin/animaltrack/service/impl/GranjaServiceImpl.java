package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.GranjaDAO;
import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;
import com.tonin.animaltrack.service.GranjaService;

public class GranjaServiceImpl implements GranjaService {

    private static Logger logger = LogManager.getLogger(GranjaServiceImpl.class.getName());

    private static final String REQUIRED_DATA_MESSAGE = "Faltan datos obligatorios. Revisa los datos introducidos.";

    private GranjaDAO granjaDAO = null;

    public GranjaServiceImpl() {
        this.granjaDAO = new GranjaDAO();
    }

    @Override
    public GranjaDTO findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            GranjaDTO result = granjaDAO.findById(c, id);
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
    public List<GranjaDTO> findByCriteria(GranjaCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<GranjaDTO> result = granjaDAO.findBy(c, criteria);
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
    public List<GranjaDTO> findByGanaderoId(Long ganaderoId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<GranjaDTO> result = granjaDAO.findByGanaderoId(c, ganaderoId);
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
    public List<GranjaDTO> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<GranjaDTO> result = granjaDAO.getAll(c);
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
    public GranjaDTO create(Granja granja) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            validateForSave(granja);
            Long id = granjaDAO.create(c, granja);
            GranjaDTO result = id == null ? null : granjaDAO.findById(c, id);
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
    public void update(Granja granja) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (granja != null && granja.getId() != null) {
                validateForSave(granja);
                granjaDAO.update(c, granja);
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
            if (id != null) {
                granjaDAO.delete(c, id);
            }
            commit = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    private void validateForSave(Granja granja) {
        if (granja == null || isBlank(granja.getRega()) || isBlank(granja.getNombre()) || isBlank(granja.getDireccion())
        || granja.getMunicipioId() == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        granja.setRega(granja.getRega().trim());
        granja.setNombre(granja.getNombre().trim());
        granja.setDireccion(granja.getDireccion().trim());
        granja.setCodigoPostal(normalize(granja.getCodigoPostal()));
    }

    private String normalize(String value) {
        return isBlank(value) ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.GanaderoDAO;
import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;
import com.tonin.animaltrack.service.GanaderoService;

public class GanaderoServiceImpl implements GanaderoService {

    private static Logger logger = LogManager.getLogger(GanaderoServiceImpl.class.getName());

    private static final String REQUIRED_DATA_MESSAGE = "Faltan datos obligatorios. Revisa los datos introducidos.";

    private GanaderoDAO ganaderoDAO = null;

    public GanaderoServiceImpl() {
        this.ganaderoDAO = new GanaderoDAO();
    }

    @Override
    public GanaderoDTO findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            GanaderoDTO result = ganaderoDAO.findById(c, id);
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
    public List<GanaderoDTO> findByCriteria(GanaderoCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<GanaderoDTO> result = ganaderoDAO.findBy(c, criteria);
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
    public List<GanaderoDTO> findByMunicipioId(Long municipioId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<GanaderoDTO> result = ganaderoDAO.findByMunicipioId(c, municipioId);
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
    public List<GanaderoDTO> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<GanaderoDTO> result = ganaderoDAO.getAll(c);
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
    public GanaderoDTO create(Ganadero ganadero) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            validateForSave(c, ganadero);
            Long id = ganaderoDAO.create(c, ganadero);
            GanaderoDTO result = id == null ? null : ganaderoDAO.findById(c, id);
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
    public void update(Ganadero ganadero) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (ganadero != null && ganadero.getId() != null) {
                validateForSave(c, ganadero);
                ganaderoDAO.update(c, ganadero);
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
                ganaderoDAO.delete(c, id);
            }
            commit = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    private void validateForSave(Connection c, Ganadero ganadero) throws Exception {
        if (ganadero == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (isBlank(ganadero.getNombre())) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (ganadero.getMunicipioId() == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }

        String dni = normalizeDni(ganadero.getDni());
        if (dni == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        ganadero.setDni(dni);
        ganadero.setDireccion(normalize(ganadero.getDireccion()));
        ganadero.setCodigoPostal(normalize(ganadero.getCodigoPostal()));

        GanaderoCriteria criteria = new GanaderoCriteria();
        criteria.setDni(dni);
        List<GanaderoDTO> matches = ganaderoDAO.findBy(c, criteria);
        if (matches == null) {
            throw new IllegalStateException("No se pudo comprobar si el DNI ya existe.");
        }
        for (GanaderoDTO existing : matches) {
            if (existing.getId() != null && !existing.getId().equals(ganadero.getId())) {
                throw new IllegalArgumentException("No se pudo guardar el ganadero. Revisa los datos introducidos.");
            }
        }
    }

    private String normalizeDni(String dni) {
        if (isBlank(dni)) {
            return null;
        }
        return dni.trim().toUpperCase();
    }

    private String normalize(String value) {
        return isBlank(value) ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

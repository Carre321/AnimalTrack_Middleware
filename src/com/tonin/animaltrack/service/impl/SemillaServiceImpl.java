package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.SemillaDAO;
import com.tonin.animaltrack.dao.criteria.SemillaCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Semilla;
import com.tonin.animaltrack.service.SemillaService;

public class SemillaServiceImpl implements SemillaService {

    private static Logger logger = LogManager.getLogger(SemillaServiceImpl.class.getName());

    private SemillaDAO semillaDAO = null;

    public SemillaServiceImpl() {
        this.semillaDAO = new SemillaDAO();
    }

    @Override
    public Semilla findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Semilla result = semillaDAO.findById(c, id);
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
    public Semilla findByCodigo(String codigo) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Semilla result = semillaDAO.findByCodigo(c, codigo);
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
    public List<Semilla> findByCriteria(SemillaCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<Semilla> result = semillaDAO.findBy(c, criteria);
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
    public List<Semilla> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<Semilla> result = semillaDAO.getAll(c);
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
    public Semilla create(Semilla semilla) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (semilla == null || semilla.getCodigo() == null || semilla.getNombre() == null) {
                commit = true;
                return null;
            }
            Long id = semillaDAO.create(c, semilla);
            Semilla result = id == null ? null : semillaDAO.findById(c, id);
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
    public void update(Semilla semilla) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (semilla != null && semilla.getId() != null) {
                semillaDAO.update(c, semilla);
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
                semillaDAO.delete(c, id);
            }
            commit = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}

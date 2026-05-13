package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.AnimalSemillaDAO;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.AnimalSemilla;
import com.tonin.animaltrack.service.AnimalSemillaService;

public class AnimalSemillaServiceImpl implements AnimalSemillaService {

    private static Logger logger = LogManager.getLogger(AnimalSemillaServiceImpl.class.getName());

    private AnimalSemillaDAO animalSemillaDAO = null;

    public AnimalSemillaServiceImpl() {
        this.animalSemillaDAO = new AnimalSemillaDAO();
    }

    @Override
    public List<AnimalSemilla> findByAnimalId(Long animalId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<AnimalSemilla> result = animalSemillaDAO.findByAnimalId(c, animalId);
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
    public List<AnimalSemilla> findBySemillaId(Long semillaId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<AnimalSemilla> result = animalSemillaDAO.findBySemillaId(c, semillaId);
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
    public AnimalSemilla create(AnimalSemilla animalSemilla) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (animalSemilla == null || animalSemilla.getAnimalId() == null || animalSemilla.getSemillaId() == null) {
                commit = true;
                return null;
            }
            animalSemillaDAO.create(c, animalSemilla);
            AnimalSemilla result = animalSemilla;
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
    public void update(Long oldAnimalId, Long oldSemillaId, AnimalSemilla animalSemilla) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (oldAnimalId != null && oldSemillaId != null && animalSemilla != null
            && animalSemilla.getAnimalId() != null && animalSemilla.getSemillaId() != null) {
                animalSemillaDAO.update(c, oldAnimalId, oldSemillaId, animalSemilla);
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
    public void delete(Long animalId, Long semillaId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (animalId != null && semillaId != null) {
                animalSemillaDAO.delete(c, animalId, semillaId);
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

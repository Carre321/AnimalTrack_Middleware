package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.VeterinarioGranjaDAO;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.VeterinarioGranja;
import com.tonin.animaltrack.service.VeterinarioGranjaService;

public class VeterinarioGranjaServiceImpl implements VeterinarioGranjaService {

    private static Logger logger = LogManager.getLogger(VeterinarioGranjaServiceImpl.class.getName());

    private VeterinarioGranjaDAO veterinarioGranjaDAO = null;

    public VeterinarioGranjaServiceImpl() {
        this.veterinarioGranjaDAO = new VeterinarioGranjaDAO();
    }

    @Override
    public List<VeterinarioGranja> findByVeterinarioId(Long veterinarioId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<VeterinarioGranja> result = veterinarioGranjaDAO.findByVeterinarioId(c, veterinarioId);
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
    public List<VeterinarioGranja> findByGranjaId(Long granjaId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<VeterinarioGranja> result = veterinarioGranjaDAO.findByGranjaId(c, granjaId);
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
    public VeterinarioGranja create(VeterinarioGranja veterinarioGranja) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (veterinarioGranja == null || veterinarioGranja.getVeterinarioId() == null || veterinarioGranja.getGranjaId() == null) {
                commit = true;
                return null;
            }
            veterinarioGranjaDAO.create(c, veterinarioGranja);
            VeterinarioGranja result = veterinarioGranja;
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
    public void update(Long oldVeterinarioId, Long oldGranjaId, VeterinarioGranja veterinarioGranja) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (oldVeterinarioId != null && oldGranjaId != null && veterinarioGranja != null
            && veterinarioGranja.getVeterinarioId() != null && veterinarioGranja.getGranjaId() != null) {
                veterinarioGranjaDAO.update(c, oldVeterinarioId, oldGranjaId, veterinarioGranja);
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
    public void delete(Long veterinarioId, Long granjaId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (veterinarioId != null && granjaId != null) {
                veterinarioGranjaDAO.delete(c, veterinarioId, granjaId);
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

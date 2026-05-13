package com.tonin.animaltrack.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.TipoNotificacionDAO;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.TipoNotificacion;
import com.tonin.animaltrack.service.TipoNotificacionService;

public class TipoNotificacionServiceImpl implements TipoNotificacionService {

    private static Logger logger = LogManager.getLogger(TipoNotificacionServiceImpl.class.getName());

    private TipoNotificacionDAO tipoNotificacionDAO = null;

    public TipoNotificacionServiceImpl() {
        this.tipoNotificacionDAO = new TipoNotificacionDAO();
    }

    @Override
    public TipoNotificacion findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            TipoNotificacion result = tipoNotificacionDAO.findById(c, id);
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
    public List<TipoNotificacion> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<TipoNotificacion> result = tipoNotificacionDAO.getAll(c);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}

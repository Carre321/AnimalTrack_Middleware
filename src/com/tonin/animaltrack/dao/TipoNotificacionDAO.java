package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.TipoNotificacion;

public class TipoNotificacionDAO {

	private static Logger logger = LogManager.getLogger(TipoNotificacionDAO.class.getName());

    private static final String BASE_QUERY = "SELECT id, nombre FROM tipo_notificacion";

    public TipoNotificacionDAO() {
    }

    public TipoNotificacion findById(Connection c, Long id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = BASE_QUERY + " WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return loadNext(rs);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
        return null;
    }

    public List<TipoNotificacion> getAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TipoNotificacion> results = new ArrayList<TipoNotificacion>();
        try {
            String sql = BASE_QUERY + " ORDER BY nombre";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                results.add(loadNext(rs));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
        return results;
    }

    private TipoNotificacion loadNext(ResultSet rs) throws Exception {
        int i = 1;
        TipoNotificacion entity = new TipoNotificacion();
        entity.setId(rs.getLong(i++));
        entity.setNombre(rs.getString(i++));
        return entity;
    }
}

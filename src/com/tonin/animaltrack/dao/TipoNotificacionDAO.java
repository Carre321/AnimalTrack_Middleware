package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.TipoNotificacion;

public class TipoNotificacionDAO {

    private static final String BASE_QUERY = "SELECT id, nombre FROM tipo_notificacion";

    public TipoNotificacionDAO() {
    }

    public TipoNotificacion findById(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return loadNext(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
        return null;
    }

    public List<TipoNotificacion> getAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TipoNotificacion> results = new ArrayList<TipoNotificacion>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " ORDER BY nombre";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                results.add(loadNext(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
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

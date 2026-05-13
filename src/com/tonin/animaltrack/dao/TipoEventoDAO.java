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
import com.tonin.animaltrack.model.TipoEvento;

public class TipoEventoDAO {

	private static Logger logger = LogManager.getLogger(TipoEventoDAO.class.getName());

    private static final String BASE_QUERY = "SELECT id, codigo, nombre FROM tipo_evento";

    public TipoEventoDAO() {
    }

    public TipoEvento findById(Connection c, Long id) throws Exception {
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

    public List<TipoEvento> getAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TipoEvento> results = new ArrayList<TipoEvento>();
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

    private TipoEvento loadNext(ResultSet rs) throws Exception {
        int i = 1;
        TipoEvento entity = new TipoEvento();
        entity.setId(rs.getLong(i++));
        entity.setCodigo(rs.getString(i++));
        entity.setNombre(rs.getString(i++));
        return entity;
    }
}

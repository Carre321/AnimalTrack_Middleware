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
import com.tonin.animaltrack.model.Tratamiento;

public class TratamientoDAO {

	private static Logger logger = LogManager.getLogger(TratamientoDAO.class.getName());

    private static final String BASE_QUERY = "SELECT id, nombre FROM tratamiento";

    public TratamientoDAO() {
    }

    public Tratamiento findById(Connection c, Long id) throws Exception {
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

    public List<Tratamiento> getAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tratamiento> results = new ArrayList<Tratamiento>();
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

    public Long create(Connection c, Tratamiento entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO tratamiento (nombre) VALUES (?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getNombre());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
        return null;
    }

    public boolean update(Connection c, Tratamiento entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE tratamiento SET nombre = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getNombre(), entity.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    public boolean delete(Connection c, Long id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "DELETE FROM tratamiento WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    private Tratamiento loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Tratamiento entity = new Tratamiento();
        entity.setId(rs.getLong(i++));
        entity.setNombre(rs.getString(i++));
        return entity;
    }
}

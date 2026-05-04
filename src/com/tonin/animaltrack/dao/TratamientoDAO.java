package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Tratamiento;

public class TratamientoDAO {

    private static final String BASE_QUERY = "SELECT id, nombre FROM tratamiento";

    public TratamientoDAO() {
    }

    public Tratamiento findById(Long id) {
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

    public List<Tratamiento> getAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tratamiento> results = new ArrayList<Tratamiento>();
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

    public Long create(Tratamiento entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO tratamiento (nombre) VALUES (?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getNombre());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
        return null;
    }

    public void update(Tratamiento entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE tratamiento SET nombre = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getNombre(), entity.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    public void delete(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "DELETE FROM tratamiento WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
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

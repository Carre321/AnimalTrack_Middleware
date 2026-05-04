package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Semilla;

public class SemillaDAO {

    private static final String BASE_QUERY = "SELECT id, codigo, descripcion FROM semilla";

    public SemillaDAO() {
    }

    public Semilla findById(Long id) {
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

    public Semilla findByCodigo(String codigo) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE codigo = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, codigo);
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

    public List<Semilla> getAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Semilla> results = new ArrayList<Semilla>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " ORDER BY codigo";
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

    public Long create(Semilla entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO semilla (codigo, descripcion) VALUES (?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getCodigo(), entity.getDescripcion());
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

    public void update(Semilla entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE semilla SET codigo = ?, descripcion = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getCodigo(), entity.getDescripcion(), entity.getId());
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
            String sql = "DELETE FROM semilla WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private Semilla loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Semilla entity = new Semilla();
        entity.setId(rs.getLong(i++));
        entity.setCodigo(rs.getString(i++));
        entity.setDescripcion(rs.getString(i++));
        return entity;
    }
}

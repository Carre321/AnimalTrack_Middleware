package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Dosis;

public class DosisDAO {

    private static final String BASE_QUERY = "SELECT id, plazo_siguiente, num_orden_dosis, tratamiento_id FROM dosis";

    public DosisDAO() {
    }

    public Dosis findById(Long id) {
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

    public List<Dosis> findByTratamientoId(Long tratamientoId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dosis> results = new ArrayList<Dosis>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE tratamiento_id = ? ORDER BY num_orden_dosis";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, tratamientoId);
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

    public List<Dosis> getAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dosis> results = new ArrayList<Dosis>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " ORDER BY id";
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

    public Long create(Dosis entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO dosis (plazo_siguiente, num_orden_dosis, tratamiento_id) VALUES (?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getPlazoSiguiente(), entity.getNumOrdenDosis(), entity.getTratamientoId());
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

    public void update(Dosis entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE dosis SET plazo_siguiente = ?, num_orden_dosis = ?, tratamiento_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getPlazoSiguiente(), entity.getNumOrdenDosis(), entity.getTratamientoId(), entity.getId());
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
            String sql = "DELETE FROM dosis WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private Dosis loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Dosis entity = new Dosis();
        entity.setId(rs.getLong(i++));
        entity.setPlazoSiguiente((Integer) rs.getObject(i++));
        entity.setNumOrdenDosis((Integer) rs.getObject(i++));
        entity.setTratamientoId(rs.getLong(i++));
        return entity;
    }
}

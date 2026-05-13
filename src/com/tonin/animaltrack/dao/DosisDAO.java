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
import com.tonin.animaltrack.model.Dosis;

public class DosisDAO {

	private static Logger logger = LogManager.getLogger(DosisDAO.class.getName());

    private static final String BASE_QUERY = "SELECT id, plazo_siguiente, num_orden_dosis, tratamiento_id FROM dosis";

    public DosisDAO() {
    }

    public Dosis findById(Connection c, Long id) throws Exception {
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

    public List<Dosis> findByTratamientoId(Connection c, Long tratamientoId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dosis> results = new ArrayList<Dosis>();
        try {
            String sql = BASE_QUERY + " WHERE tratamiento_id = ? ORDER BY num_orden_dosis";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, tratamientoId);
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

    public List<Dosis> getAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dosis> results = new ArrayList<Dosis>();
        try {
            String sql = BASE_QUERY + " ORDER BY id";
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

    public Long create(Connection c, Dosis entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO dosis (plazo_siguiente, num_orden_dosis, tratamiento_id) VALUES (?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getPlazoSiguiente(), entity.getNumOrdenDosis(), entity.getTratamientoId());
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

    public boolean update(Connection c, Dosis entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE dosis SET plazo_siguiente = ?, num_orden_dosis = ?, tratamiento_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getPlazoSiguiente(), entity.getNumOrdenDosis(), entity.getTratamientoId(), entity.getId());
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
            String sql = "DELETE FROM dosis WHERE id = ?";
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

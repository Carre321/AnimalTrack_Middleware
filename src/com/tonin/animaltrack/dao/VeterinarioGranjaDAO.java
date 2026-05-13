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
import com.tonin.animaltrack.model.VeterinarioGranja;

public class VeterinarioGranjaDAO {

	private static Logger logger = LogManager.getLogger(VeterinarioGranjaDAO.class.getName());

    private static final String BASE_QUERY = "SELECT veterinario_id, granja_id FROM veterinario_granja";

    public VeterinarioGranjaDAO() {
    }

    public List<VeterinarioGranja> findByVeterinarioId(Connection c, Long veterinarioId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VeterinarioGranja> results = new ArrayList<VeterinarioGranja>();
        try {
            String sql = BASE_QUERY + " WHERE veterinario_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, veterinarioId);
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

    public List<VeterinarioGranja> findByGranjaId(Connection c, Long granjaId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VeterinarioGranja> results = new ArrayList<VeterinarioGranja>();
        try {
            String sql = BASE_QUERY + " WHERE granja_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, granjaId);
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

    public void create(Connection c, VeterinarioGranja entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO veterinario_granja (veterinario_id, granja_id) VALUES (?, ?)";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getVeterinarioId(), entity.getGranjaId());
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    public boolean update(Connection c, Long oldVeterinarioId, Long oldGranjaId, VeterinarioGranja entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE veterinario_granja SET veterinario_id = ?, granja_id = ? WHERE veterinario_id = ? AND granja_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getVeterinarioId(), entity.getGranjaId(), oldVeterinarioId, oldGranjaId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    public boolean delete(Connection c, Long veterinarioId, Long granjaId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "DELETE FROM veterinario_granja WHERE veterinario_id = ? AND granja_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, veterinarioId, granjaId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    private VeterinarioGranja loadNext(ResultSet rs) throws Exception {
        int i = 1;
        VeterinarioGranja entity = new VeterinarioGranja();
        entity.setVeterinarioId(rs.getLong(i++));
        entity.setGranjaId(rs.getLong(i++));
        return entity;
    }
}

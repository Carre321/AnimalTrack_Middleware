package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.VeterinarioGranja;

public class VeterinarioGranjaDAO {

    private static final String BASE_QUERY = "SELECT veterinario_id, granja_id FROM veterinario_granja";

    public VeterinarioGranjaDAO() {
    }

    public List<VeterinarioGranja> findByVeterinarioId(Long veterinarioId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VeterinarioGranja> results = new ArrayList<VeterinarioGranja>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE veterinario_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, veterinarioId);
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

    public List<VeterinarioGranja> findByGranjaId(Long granjaId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VeterinarioGranja> results = new ArrayList<VeterinarioGranja>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE granja_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, granjaId);
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

    public void create(VeterinarioGranja entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO veterinario_granja (veterinario_id, granja_id) VALUES (?, ?)";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getVeterinarioId(), entity.getGranjaId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    public void update(Long oldVeterinarioId, Long oldGranjaId, VeterinarioGranja entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE veterinario_granja SET veterinario_id = ?, granja_id = ? WHERE veterinario_id = ? AND granja_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getVeterinarioId(), entity.getGranjaId(), oldVeterinarioId, oldGranjaId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    public void delete(Long veterinarioId, Long granjaId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "DELETE FROM veterinario_granja WHERE veterinario_id = ? AND granja_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, veterinarioId, granjaId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
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

package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.AnimalSemilla;

public class AnimalSemillaDAO {

    private static final String BASE_QUERY = "SELECT animal_id, semilla_id FROM animal_semilla";

    public AnimalSemillaDAO() {
    }

    public List<AnimalSemilla> findByAnimalId(Long animalId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnimalSemilla> results = new ArrayList<AnimalSemilla>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE animal_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, animalId);
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

    public List<AnimalSemilla> findBySemillaId(Long semillaId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnimalSemilla> results = new ArrayList<AnimalSemilla>();
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE semilla_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, semillaId);
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

    public void create(AnimalSemilla entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO animal_semilla (animal_id, semilla_id) VALUES (?, ?)";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getSemillaId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    public void update(Long oldAnimalId, Long oldSemillaId, AnimalSemilla entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE animal_semilla SET animal_id = ?, semilla_id = ? WHERE animal_id = ? AND semilla_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getSemillaId(), oldAnimalId, oldSemillaId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    public void delete(Long animalId, Long semillaId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "DELETE FROM animal_semilla WHERE animal_id = ? AND semilla_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, animalId, semillaId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private AnimalSemilla loadNext(ResultSet rs) throws Exception {
        int i = 1;
        AnimalSemilla entity = new AnimalSemilla();
        entity.setAnimalId(rs.getLong(i++));
        entity.setSemillaId(rs.getLong(i++));
        return entity;
    }
}

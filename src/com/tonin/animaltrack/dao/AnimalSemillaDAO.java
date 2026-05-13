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
import com.tonin.animaltrack.model.AnimalSemilla;

public class AnimalSemillaDAO {

	private static Logger logger = LogManager.getLogger(AnimalSemillaDAO.class.getName());

    private static final String BASE_QUERY = "SELECT animal_id, semilla_id FROM animal_semilla";

    public AnimalSemillaDAO() {
    }

    public List<AnimalSemilla> findByAnimalId(Connection c, Long animalId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnimalSemilla> results = new ArrayList<AnimalSemilla>();
        try {
            String sql = BASE_QUERY + " WHERE animal_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, animalId);
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

    public List<AnimalSemilla> findBySemillaId(Connection c, Long semillaId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AnimalSemilla> results = new ArrayList<AnimalSemilla>();
        try {
            String sql = BASE_QUERY + " WHERE semilla_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, semillaId);
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

    public void create(Connection c, AnimalSemilla entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO animal_semilla (animal_id, semilla_id) VALUES (?, ?)";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getSemillaId());
            ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    public boolean update(Connection c, Long oldAnimalId, Long oldSemillaId, AnimalSemilla entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE animal_semilla SET animal_id = ?, semilla_id = ? WHERE animal_id = ? AND semilla_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getSemillaId(), oldAnimalId, oldSemillaId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    public boolean delete(Connection c, Long animalId, Long semillaId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "DELETE FROM animal_semilla WHERE animal_id = ? AND semilla_id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, animalId, semillaId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
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

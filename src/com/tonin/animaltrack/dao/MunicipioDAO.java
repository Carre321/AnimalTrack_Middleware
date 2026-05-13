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
import com.tonin.animaltrack.model.Municipio;

public class MunicipioDAO {

	private static Logger logger = LogManager.getLogger(MunicipioDAO.class.getName());

    private static final String BASE_QUERY = "SELECT id, nombre, provincia_id FROM municipio";

    public MunicipioDAO() {
    }

    public Municipio findById(Connection c, Long id) throws Exception {
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

    public List<Municipio> findByProvinciaId(Connection c, Long provinciaId) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Municipio> results = new ArrayList<Municipio>();
        try {
            String sql = BASE_QUERY + " WHERE provincia_id = ? ORDER BY nombre";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, provinciaId);
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

    public List<Municipio> getAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Municipio> results = new ArrayList<Municipio>();
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

    private Municipio loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Municipio entity = new Municipio();
        entity.setId(rs.getLong(i++));
        entity.setNombre(rs.getString(i++));
        entity.setProvinciaId(rs.getLong(i++));
        return entity;
    }
}

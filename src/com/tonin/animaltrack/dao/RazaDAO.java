package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Raza;

public class RazaDAO {

    private static final String BASE_QUERY = "SELECT id, nombre FROM raza";

    public RazaDAO() {
    }

    public Raza findById(Long id) {
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

    public List<Raza> getAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Raza> results = new ArrayList<Raza>();
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

    private Raza loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Raza entity = new Raza();
        entity.setId(rs.getLong(i++));
        entity.setNombre(rs.getString(i++));
        return entity;
    }
}

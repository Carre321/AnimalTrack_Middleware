package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;

public class GranjaDAO {

    private static final String BASE_QUERY =
            "SELECT g.id, g.nombre, g.direccion, g.municipio_id, m.nombre, p.id, p.nombre, g.ganadero_id, " +
            "TRIM(CONCAT(COALESCE(ga.nombre,''), ' ', COALESCE(ga.apellidos,''))) " +
            "FROM granja g " +
            "INNER JOIN municipio m ON g.municipio_id = m.id " +
            "INNER JOIN provincia p ON m.provincia_id = p.id " +
            "INNER JOIN ganadero ga ON g.ganadero_id = ga.id ";

    public GranjaDAO() {
    }

    public GranjaDTO findById(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE g.id = ?";
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

    public List<GranjaDTO> findBy(GranjaCriteria criteria) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            List<String> condiciones = new ArrayList<String>();
            List<Object> parametros = new ArrayList<Object>();

            SQLUtils.addClause(criteria.getId(), condiciones, "g.id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getNombre(), condiciones, "g.nombre = ?", parametros, criteria.getNombre());
            SQLUtils.addClause(criteria.getMunicipioId(), condiciones, "g.municipio_id = ?", parametros, criteria.getMunicipioId());
            SQLUtils.addClause(criteria.getGanaderoId(), condiciones, "g.ganadero_id = ?", parametros, criteria.getGanaderoId());

            if (criteria.getNombreLike() != null) {
                SQLUtils.addClause(criteria.getNombreLike(), condiciones, "UPPER(g.nombre) LIKE UPPER(?)", parametros, "%" + criteria.getNombreLike() + "%");
            }
            if (criteria.getDireccionLike() != null) {
                SQLUtils.addClause(criteria.getDireccionLike(), condiciones, "UPPER(g.direccion) LIKE UPPER(?)", parametros, "%" + criteria.getDireccionLike() + "%");
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY g.nombre");

            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, parametros);
            rs = ps.executeQuery();

            List<GranjaDTO> results = new ArrayList<GranjaDTO>();
            while (rs.next()) {
                results.add(loadNext(rs));
            }
            return results;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
        return null;
    }

    public List<GranjaDTO> findByGanaderoId(Long ganaderoId) {
        GranjaCriteria criteria = new GranjaCriteria();
        criteria.setGanaderoId(ganaderoId);
        return findBy(criteria);
    }

    public List<GranjaDTO> getAll() {
        return findBy(new GranjaCriteria());
    }

    public Long create(Granja entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO granja (nombre, direccion, municipio_id, ganadero_id) VALUES (?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getNombre(), entity.getDireccion(), entity.getMunicipioId(), entity.getGanaderoId());
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

    public void update(Granja entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE granja SET nombre = ?, direccion = ?, municipio_id = ?, ganadero_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getNombre(), entity.getDireccion(), entity.getMunicipioId(), entity.getGanaderoId(), entity.getId());
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
            String sql = "DELETE FROM granja WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private GranjaDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        GranjaDTO dto = new GranjaDTO();
        dto.setId(rs.getLong(i++));
        dto.setNombre(rs.getString(i++));
        dto.setDireccion(rs.getString(i++));
        dto.setMunicipioId(rs.getLong(i++));
        dto.setMunicipioNombre(rs.getString(i++));
        dto.setProvinciaId(rs.getLong(i++));
        dto.setProvinciaNombre(rs.getString(i++));
        dto.setGanaderoId(rs.getLong(i++));
        dto.setGanaderoNombreCompleto(rs.getString(i++));
        return dto;
    }
}

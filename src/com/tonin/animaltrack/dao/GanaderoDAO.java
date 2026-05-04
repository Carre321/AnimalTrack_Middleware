package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;

public class GanaderoDAO {

    private static final String BASE_QUERY =
            "SELECT g.id, g.dni, g.nombre, g.apellidos, g.telefono, g.email, g.municipio_id, m.nombre, p.id, p.nombre " +
            "FROM ganadero g " +
            "INNER JOIN municipio m ON g.municipio_id = m.id " +
            "INNER JOIN provincia p ON m.provincia_id = p.id ";

    public GanaderoDAO() {
    }

    public GanaderoDTO findById(Long id) {
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

    public List<GanaderoDTO> findBy(GanaderoCriteria criteria) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            List<String> condiciones = new ArrayList<String>();
            List<Object> parametros = new ArrayList<Object>();

            SQLUtils.addClause(criteria.getId(), condiciones, "g.id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getDni(), condiciones, "g.dni = ?", parametros, criteria.getDni());
            SQLUtils.addClause(criteria.getMunicipioId(), condiciones, "g.municipio_id = ?", parametros, criteria.getMunicipioId());

            if (criteria.getDniLike() != null) {
                SQLUtils.addClause(criteria.getDniLike(), condiciones, "UPPER(g.dni) LIKE UPPER(?)", parametros, "%" + criteria.getDniLike() + "%");
            }
            if (criteria.getNombreLike() != null) {
                SQLUtils.addClause(criteria.getNombreLike(), condiciones, "UPPER(g.nombre) LIKE UPPER(?)", parametros, "%" + criteria.getNombreLike() + "%");
            }
            if (criteria.getApellidosLike() != null) {
                SQLUtils.addClause(criteria.getApellidosLike(), condiciones, "UPPER(g.apellidos) LIKE UPPER(?)", parametros, "%" + criteria.getApellidosLike() + "%");
            }
            if (criteria.getTelefonoLike() != null) {
                SQLUtils.addClause(criteria.getTelefonoLike(), condiciones, "UPPER(g.telefono) LIKE UPPER(?)", parametros, "%" + criteria.getTelefonoLike() + "%");
            }
            if (criteria.getEmailLike() != null) {
                SQLUtils.addClause(criteria.getEmailLike(), condiciones, "UPPER(g.email) LIKE UPPER(?)", parametros, "%" + criteria.getEmailLike() + "%");
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY g.apellidos, g.nombre");

            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, parametros);
            rs = ps.executeQuery();

            List<GanaderoDTO> results = new ArrayList<GanaderoDTO>();
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

    public List<GanaderoDTO> findByMunicipioId(Long municipioId) {
        GanaderoCriteria criteria = new GanaderoCriteria();
        criteria.setMunicipioId(municipioId);
        return findBy(criteria);
    }

    public List<GanaderoDTO> getAll() {
        return findBy(new GanaderoCriteria());
    }

    public Long create(Ganadero entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO ganadero (dni, nombre, apellidos, telefono, email, municipio_id) VALUES (?, ?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getDni(), entity.getNombre(), entity.getApellidos(), entity.getTelefono(), entity.getEmail(), entity.getMunicipioId());
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

    public void update(Ganadero entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE ganadero SET dni = ?, nombre = ?, apellidos = ?, telefono = ?, email = ?, municipio_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getDni(), entity.getNombre(), entity.getApellidos(), entity.getTelefono(), entity.getEmail(), entity.getMunicipioId(), entity.getId());
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
            String sql = "DELETE FROM ganadero WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private GanaderoDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        GanaderoDTO dto = new GanaderoDTO();
        dto.setId(rs.getLong(i++));
        dto.setDni(rs.getString(i++));
        dto.setNombre(rs.getString(i++));
        dto.setApellidos(rs.getString(i++));
        dto.setTelefono(rs.getString(i++));
        dto.setEmail(rs.getString(i++));
        dto.setMunicipioId(rs.getLong(i++));
        dto.setMunicipioNombre(rs.getString(i++));
        dto.setProvinciaId(rs.getLong(i++));
        dto.setProvinciaNombre(rs.getString(i++));
        return dto;
    }
}

package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;

public class GanaderoDAO {

	private static Logger logger = LogManager.getLogger(GanaderoDAO.class.getName());

    private static final String BASE_QUERY =
            "SELECT g.id, g.dni, g.nombre, g.apellidos, g.telefono, g.email, g.direccion, g.codigo_postal, g.municipio_id, m.nombre, p.id, p.nombre " +
            "FROM ganadero g " +
            "INNER JOIN municipio m ON g.municipio_id = m.id " +
            "INNER JOIN provincia p ON m.provincia_id = p.id ";

    public GanaderoDAO() {
    }

    public GanaderoDTO findById(Connection c, Long id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = BASE_QUERY + " WHERE g.id = ?";
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

    public List<GanaderoDTO> findBy(Connection c, GanaderoCriteria criteria) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

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
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
        }

    public List<GanaderoDTO> findByMunicipioId(Connection c, Long municipioId) throws Exception {
        GanaderoCriteria criteria = new GanaderoCriteria();
        criteria.setMunicipioId(municipioId);
        return findBy(c, criteria);
    }

    public List<GanaderoDTO> getAll(Connection c) throws Exception {
        return findBy(c, new GanaderoCriteria());
    }

    public Long create(Connection c, Ganadero entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO ganadero (dni, nombre, apellidos, telefono, email, direccion, codigo_postal, municipio_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getDni(), entity.getNombre(), entity.getApellidos(), entity.getTelefono(), entity.getEmail(), entity.getDireccion(), entity.getCodigoPostal(), entity.getMunicipioId());
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

    public boolean update(Connection c, Ganadero entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE ganadero SET dni = ?, nombre = ?, apellidos = ?, telefono = ?, email = ?, direccion = ?, codigo_postal = ?, municipio_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getDni(), entity.getNombre(), entity.getApellidos(), entity.getTelefono(), entity.getEmail(), entity.getDireccion(), entity.getCodigoPostal(), entity.getMunicipioId(), entity.getId());
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
            String sql = "DELETE FROM ganadero WHERE id = ?";
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

    private GanaderoDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        GanaderoDTO dto = new GanaderoDTO();
        dto.setId(rs.getLong(i++));
        dto.setDni(rs.getString(i++));
        dto.setNombre(rs.getString(i++));
        dto.setApellidos(rs.getString(i++));
        dto.setTelefono(rs.getString(i++));
        dto.setEmail(rs.getString(i++));
        dto.setDireccion(rs.getString(i++));
        dto.setCodigoPostal(rs.getString(i++));
        dto.setMunicipioId(rs.getLong(i++));
        dto.setMunicipioNombre(rs.getString(i++));
        dto.setProvinciaId(rs.getLong(i++));
        dto.setProvinciaNombre(rs.getString(i++));
        return dto;
    }
}

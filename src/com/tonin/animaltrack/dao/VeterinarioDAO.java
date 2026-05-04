package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;

public class VeterinarioDAO {

    private static final String BASE_QUERY =
            "SELECT v.id, v.codigo, v.dni, v.nombre, v.apellidos, v.telefono, v.email, v.municipio_id, m.nombre, p.id, p.nombre " +
            "FROM veterinario v " +
            "INNER JOIN municipio m ON v.municipio_id = m.id " +
            "INNER JOIN provincia p ON m.provincia_id = p.id ";

    public VeterinarioDAO() {
    }

    public VeterinarioDTO findById(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE v.id = ?";
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

    public List<VeterinarioDTO> findBy(VeterinarioCriteria criteria) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            if (criteria.getGranjaId() != null) {
                sql.append("INNER JOIN veterinario_granja vg ON vg.veterinario_id = v.id ");
            }
            List<String> condiciones = new ArrayList<String>();
            List<Object> parametros = new ArrayList<Object>();

            SQLUtils.addClause(criteria.getId(), condiciones, "v.id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getCodigo(), condiciones, "v.codigo = ?", parametros, criteria.getCodigo());
            SQLUtils.addClause(criteria.getDni(), condiciones, "v.dni = ?", parametros, criteria.getDni());
            SQLUtils.addClause(criteria.getMunicipioId(), condiciones, "v.municipio_id = ?", parametros, criteria.getMunicipioId());
            SQLUtils.addClause(criteria.getGranjaId(), condiciones, "vg.granja_id = ?", parametros, criteria.getGranjaId());

            if (criteria.getCodigoLike() != null) {
                SQLUtils.addClause(criteria.getCodigoLike(), condiciones, "UPPER(v.codigo) LIKE UPPER(?)", parametros, "%" + criteria.getCodigoLike() + "%");
            }
            if (criteria.getDniLike() != null) {
                SQLUtils.addClause(criteria.getDniLike(), condiciones, "UPPER(v.dni) LIKE UPPER(?)", parametros, "%" + criteria.getDniLike() + "%");
            }
            if (criteria.getNombreLike() != null) {
                SQLUtils.addClause(criteria.getNombreLike(), condiciones, "UPPER(v.nombre) LIKE UPPER(?)", parametros, "%" + criteria.getNombreLike() + "%");
            }
            if (criteria.getApellidosLike() != null) {
                SQLUtils.addClause(criteria.getApellidosLike(), condiciones, "UPPER(v.apellidos) LIKE UPPER(?)", parametros, "%" + criteria.getApellidosLike() + "%");
            }
            if (criteria.getTelefonoLike() != null) {
                SQLUtils.addClause(criteria.getTelefonoLike(), condiciones, "UPPER(v.telefono) LIKE UPPER(?)", parametros, "%" + criteria.getTelefonoLike() + "%");
            }
            if (criteria.getEmailLike() != null) {
                SQLUtils.addClause(criteria.getEmailLike(), condiciones, "UPPER(v.email) LIKE UPPER(?)", parametros, "%" + criteria.getEmailLike() + "%");
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY v.apellidos, v.nombre");

            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, parametros);
            rs = ps.executeQuery();

            List<VeterinarioDTO> results = new ArrayList<VeterinarioDTO>();
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

    public List<VeterinarioDTO> findByMunicipioId(Long municipioId) {
        VeterinarioCriteria criteria = new VeterinarioCriteria();
        criteria.setMunicipioId(municipioId);
        return findBy(criteria);
    }

    public List<VeterinarioDTO> getAll() {
        return findBy(new VeterinarioCriteria());
    }

    public Long create(Veterinario entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO veterinario (codigo, dni, nombre, apellidos, telefono, email, municipio_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getCodigo(), entity.getDni(), entity.getNombre(), entity.getApellidos(), entity.getTelefono(), entity.getEmail(), entity.getMunicipioId());
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

    public void update(Veterinario entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE veterinario SET codigo = ?, dni = ?, nombre = ?, apellidos = ?, telefono = ?, email = ?, municipio_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getCodigo(), entity.getDni(), entity.getNombre(), entity.getApellidos(), entity.getTelefono(), entity.getEmail(), entity.getMunicipioId(), entity.getId());
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
            String sql = "DELETE FROM veterinario WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private VeterinarioDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        VeterinarioDTO dto = new VeterinarioDTO();
        dto.setId(rs.getLong(i++));
        dto.setCodigo(rs.getString(i++));
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

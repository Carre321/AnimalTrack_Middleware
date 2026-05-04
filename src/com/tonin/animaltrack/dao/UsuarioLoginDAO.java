package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;

public class UsuarioLoginDAO {

    private static final String BASE_QUERY =
            "SELECT ul.id, ul.email, ul.password_hash, ul.rol, ul.ganadero_id, " +
            "TRIM(CONCAT(COALESCE(g.nombre,''), ' ', COALESCE(g.apellidos,''))), " +
            "ul.veterinario_id, TRIM(CONCAT(COALESCE(v.nombre,''), ' ', COALESCE(v.apellidos,''))), " +
            "ul.activo " +
            "FROM usuario_login ul " +
            "LEFT JOIN ganadero g ON ul.ganadero_id = g.id " +
            "LEFT JOIN veterinario v ON ul.veterinario_id = v.id ";

    public UsuarioLoginDAO() {
    }

    public UsuarioLoginDTO findById(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            ps = c.prepareStatement(BASE_QUERY + " WHERE ul.id = ?");
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

    public UsuarioLoginDTO findByEmail(String email) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            ps = c.prepareStatement(BASE_QUERY + " WHERE UPPER(ul.email) = UPPER(?)");
            DAOUtils.setParameters(ps, email);
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

    public List<UsuarioLoginDTO> findAll() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            ps = c.prepareStatement(BASE_QUERY + " ORDER BY ul.email");
            rs = ps.executeQuery();
            List<UsuarioLoginDTO> results = new ArrayList<UsuarioLoginDTO>();
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

    public Long create(UsuarioLoginDTO entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO usuario_login (email, password_hash, rol, ganadero_id, veterinario_id, activo) VALUES (?, ?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getEmail(), entity.getPasswordHash(), entity.getRol(), entity.getGanaderoId(),
                    entity.getVeterinarioId(), entity.getActivo());
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

    public void update(UsuarioLoginDTO entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE usuario_login SET email = ?, password_hash = ?, rol = ?, ganadero_id = ?, veterinario_id = ?, activo = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getEmail(), entity.getPasswordHash(), entity.getRol(), entity.getGanaderoId(),
                    entity.getVeterinarioId(), entity.getActivo(), entity.getId());
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
            ps = c.prepareStatement("DELETE FROM usuario_login WHERE id = ?");
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private UsuarioLoginDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        UsuarioLoginDTO dto = new UsuarioLoginDTO();
        dto.setId(rs.getLong(i++));
        dto.setEmail(rs.getString(i++));
        dto.setPasswordHash(rs.getString(i++));
        dto.setRol(rs.getString(i++));
        dto.setGanaderoId((Long) rs.getObject(i++));
        dto.setGanaderoNombreCompleto(rs.getString(i++));
        dto.setVeterinarioId((Long) rs.getObject(i++));
        dto.setVeterinarioNombreCompleto(rs.getString(i++));
        Object activo = rs.getObject(i++);
        dto.setActivo(activo == null ? Boolean.FALSE : ((Boolean) activo));
        return dto;
    }
}

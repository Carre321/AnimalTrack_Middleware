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
import com.tonin.animaltrack.model.dto.UsuarioLoginDTO;

public class UsuarioLoginDAO {

	private static Logger logger = LogManager.getLogger(UsuarioLoginDAO.class.getName());

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

    public UsuarioLoginDTO findById(Connection c, Long id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(BASE_QUERY + " WHERE ul.id = ?");
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

    public UsuarioLoginDTO findByEmail(Connection c, String email) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(BASE_QUERY + " WHERE UPPER(ul.email) = UPPER(?)");
            DAOUtils.setParameters(ps, email);
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

    public List<UsuarioLoginDTO> findAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(BASE_QUERY + " ORDER BY ul.email");
            rs = ps.executeQuery();
            List<UsuarioLoginDTO> results = new ArrayList<UsuarioLoginDTO>();
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

    public Long create(Connection c, UsuarioLoginDTO entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
        return null;
    }

    public boolean update(Connection c, UsuarioLoginDTO entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE usuario_login SET email = ?, password_hash = ?, rol = ?, ganadero_id = ?, veterinario_id = ?, activo = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getEmail(), entity.getPasswordHash(), entity.getRol(), entity.getGanaderoId(),
                    entity.getVeterinarioId(), entity.getActivo(), entity.getId());
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
            ps = c.prepareStatement("DELETE FROM usuario_login WHERE id = ?");
            DAOUtils.setParameters(ps, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
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

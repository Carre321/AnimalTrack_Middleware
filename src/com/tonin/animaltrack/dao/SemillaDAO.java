package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.criteria.SemillaCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Semilla;

public class SemillaDAO {

	private static Logger logger = LogManager.getLogger(SemillaDAO.class.getName());

    private static final String BASE_QUERY = "SELECT s.id, s.codigo, s.nombre, s.descripcion, s.raza_id, r.nombre AS raza_nombre "
            + "FROM semilla s LEFT JOIN raza r ON r.id = s.raza_id";

    public SemillaDAO() {
    }

    public Semilla findById(Connection c, Long id) throws Exception {
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

    public Semilla findByCodigo(Connection c, String codigo) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = BASE_QUERY + " WHERE codigo = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, codigo);
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

    public List<Semilla> findBy(Connection c, SemillaCriteria criteria) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Semilla> results = new ArrayList<Semilla>();
        try {
            StringBuilder sql = new StringBuilder(BASE_QUERY);
            List<Object> params = new ArrayList<Object>();
            boolean whereAdded = false;

            if (criteria != null && criteria.getCodigoLike() != null) {
                sql.append(whereAdded ? " AND " : " WHERE ");
                sql.append("UPPER(s.codigo) LIKE UPPER(?)");
                params.add("%" + criteria.getCodigoLike() + "%");
                whereAdded = true;
            }
            if (criteria != null && criteria.getNombreLike() != null) {
                sql.append(whereAdded ? " AND " : " WHERE ");
                sql.append("UPPER(s.nombre) LIKE UPPER(?)");
                params.add("%" + criteria.getNombreLike() + "%");
                whereAdded = true;
            }
            if (criteria != null && criteria.getRazaId() != null) {
                sql.append(whereAdded ? " AND " : " WHERE ");
                sql.append("s.raza_id = ?");
                params.add(criteria.getRazaId());
            }

            sql.append(" ORDER BY s.codigo");
            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, params);
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

    public List<Semilla> getAll(Connection c) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Semilla> results = new ArrayList<Semilla>();
        try {
            String sql = BASE_QUERY + " ORDER BY s.codigo";
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

    public Long create(Connection c, Semilla entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO semilla (codigo, nombre, descripcion, raza_id) VALUES (?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            DAOUtils.setParameters(ps, entity.getCodigo(), entity.getNombre(), entity.getDescripcion(), entity.getRazaId());
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

    public boolean update(Connection c, Semilla entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE semilla SET codigo = ?, nombre = ?, descripcion = ?, raza_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, entity.getCodigo(), entity.getNombre(), entity.getDescripcion(), entity.getRazaId(),
                    entity.getId());
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
            String sql = "DELETE FROM semilla WHERE id = ?";
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

    private Semilla loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Semilla entity = new Semilla();
        entity.setId(rs.getLong(i++));
        entity.setCodigo(rs.getString(i++));
        entity.setNombre(rs.getString(i++));
        entity.setDescripcion(rs.getString(i++));
        entity.setRazaId((Long) rs.getObject(i++));
        entity.setRazaNombre(rs.getString(i++));
        return entity;
    }
}

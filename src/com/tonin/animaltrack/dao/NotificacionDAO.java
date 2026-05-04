package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.NotificacionCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Notificacion;
import com.tonin.animaltrack.model.dto.NotificacionDTO;

public class NotificacionDAO {

    private static final String BASE_QUERY =
            "SELECT n.id, n.evento_id, n.tipo, n.fecha_emision, n.descripcion, n.tipo_notificacion_id, tn.nombre, " +
            "e.animal_id, a.crotal, te.nombre " +
            "FROM notificacion n " +
            "INNER JOIN tipo_notificacion tn ON n.tipo_notificacion_id = tn.id " +
            "INNER JOIN evento e ON n.evento_id = e.id " +
            "INNER JOIN animal a ON e.animal_id = a.id " +
            "INNER JOIN tipo_evento te ON e.tipo_evento_id = te.id ";

    public NotificacionDAO() {
    }

    public NotificacionDTO findById(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE n.id = ?";
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

    public List<NotificacionDTO> findBy(NotificacionCriteria criteria) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            List<String> condiciones = new ArrayList<String>();
            List<Object> parametros = new ArrayList<Object>();

            SQLUtils.addClause(criteria.getId(), condiciones, "n.id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getEventoId(), condiciones, "n.evento_id = ?", parametros, criteria.getEventoId());
            SQLUtils.addClause(criteria.getTipo(), condiciones, "n.tipo = ?", parametros, criteria.getTipo());
            SQLUtils.addClause(criteria.getTipoNotificacionId(), condiciones, "n.tipo_notificacion_id = ?", parametros, criteria.getTipoNotificacionId());

            if (criteria.getDescripcionLike() != null) {
                SQLUtils.addClause(criteria.getDescripcionLike(), condiciones, "UPPER(n.descripcion) LIKE UPPER(?)", parametros, "%" + criteria.getDescripcionLike() + "%");
            }
            if (criteria.getFechaEmisionDesde() != null) {
                SQLUtils.addClause(criteria.getFechaEmisionDesde(), condiciones, "n.fecha_emision >= ?", parametros, Timestamp.valueOf(criteria.getFechaEmisionDesde()));
            }
            if (criteria.getFechaEmisionHasta() != null) {
                SQLUtils.addClause(criteria.getFechaEmisionHasta(), condiciones, "n.fecha_emision <= ?", parametros, Timestamp.valueOf(criteria.getFechaEmisionHasta()));
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY n.fecha_emision DESC");

            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, parametros);
            rs = ps.executeQuery();

            List<NotificacionDTO> results = new ArrayList<NotificacionDTO>();
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

    public List<NotificacionDTO> findByEventoId(Long eventoId) {
        NotificacionCriteria criteria = new NotificacionCriteria();
        criteria.setEventoId(eventoId);
        return findBy(criteria);
    }

    public List<NotificacionDTO> getAll() {
        return findBy(new NotificacionCriteria());
    }

    public Long create(Notificacion entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO notificacion (evento_id, tipo, fecha_emision, descripcion, tipo_notificacion_id) VALUES (?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Timestamp ts = entity.getFechaEmision() == null ? null : Timestamp.valueOf(entity.getFechaEmision());
            DAOUtils.setParameters(ps, entity.getEventoId(), entity.getTipo(), ts, entity.getDescripcion(), entity.getTipoNotificacionId());
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

    public void update(Notificacion entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE notificacion SET evento_id = ?, tipo = ?, fecha_emision = ?, descripcion = ?, tipo_notificacion_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            Timestamp ts = entity.getFechaEmision() == null ? null : Timestamp.valueOf(entity.getFechaEmision());
            DAOUtils.setParameters(ps, entity.getEventoId(), entity.getTipo(), ts, entity.getDescripcion(), entity.getTipoNotificacionId(), entity.getId());
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
            String sql = "DELETE FROM notificacion WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private NotificacionDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(rs.getLong(i++));
        dto.setEventoId(rs.getLong(i++));
        dto.setTipo(rs.getString(i++));
        Timestamp ts = rs.getTimestamp(i++);
        dto.setFechaEmision(ts == null ? null : ts.toLocalDateTime());
        dto.setDescripcion(rs.getString(i++));
        dto.setTipoNotificacionId(rs.getLong(i++));
        dto.setTipoNotificacionNombre(rs.getString(i++));
        dto.setAnimalId(rs.getLong(i++));
        dto.setAnimalCrotal(rs.getString(i++));
        dto.setTipoEventoNombre(rs.getString(i++));
        return dto;
    }
}

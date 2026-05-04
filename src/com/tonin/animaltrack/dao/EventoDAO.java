package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;

public class EventoDAO {

    private static final String BASE_QUERY =
            "SELECT e.id, e.animal_id, a.crotal, a.nombre, e.tipo_evento_id, te.nombre, " +
            "e.veterinario_id, TRIM(CONCAT(COALESCE(v.nombre,''), ' ', COALESCE(v.apellidos,''))), " +
            "e.fecha_hora, e.semilla_id, s.codigo, e.precio_evento, e.dosis_id, d.num_orden_dosis, " +
            "e.tratamiento_id, t.nombre " +
            "FROM evento e " +
            "INNER JOIN animal a ON e.animal_id = a.id " +
            "INNER JOIN tipo_evento te ON e.tipo_evento_id = te.id " +
            "LEFT JOIN veterinario v ON e.veterinario_id = v.id " +
            "LEFT JOIN semilla s ON e.semilla_id = s.id " +
            "LEFT JOIN dosis d ON e.dosis_id = d.id " +
            "LEFT JOIN tratamiento t ON e.tratamiento_id = t.id ";

    public EventoDAO() {
    }

    public EventoDTO findById(Long id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = BASE_QUERY + " WHERE e.id = ?";
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

    public List<EventoDTO> findBy(EventoCriteria criteria) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            List<String> condiciones = new ArrayList<String>();
            List<Object> parametros = new ArrayList<Object>();

            SQLUtils.addClause(criteria.getId(), condiciones, "e.id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getAnimalId(), condiciones, "e.animal_id = ?", parametros, criteria.getAnimalId());
            SQLUtils.addClause(criteria.getGranjaId(), condiciones, "a.granja_id = ?", parametros, criteria.getGranjaId());
            SQLUtils.addClause(criteria.getAnimalNombreLike(), condiciones, "UPPER(a.nombre) LIKE UPPER(?)", parametros,
                    "%" + criteria.getAnimalNombreLike() + "%");
            SQLUtils.addClause(criteria.getAnimalCrotalLike(), condiciones, "UPPER(a.crotal) LIKE UPPER(?)", parametros,
                    "%" + criteria.getAnimalCrotalLike() + "%");
            SQLUtils.addClause(criteria.getTipoEventoId(), condiciones, "e.tipo_evento_id = ?", parametros, criteria.getTipoEventoId());
            SQLUtils.addClause(criteria.getTipoEventoNombreLike(), condiciones, "UPPER(te.nombre) LIKE UPPER(?)", parametros,
                    "%" + criteria.getTipoEventoNombreLike() + "%");
            SQLUtils.addClause(criteria.getVeterinarioId(), condiciones, "e.veterinario_id = ?", parametros, criteria.getVeterinarioId());
            SQLUtils.addClause(criteria.getVeterinarioNombreLike(), condiciones,
                    "UPPER(TRIM(CONCAT(COALESCE(v.nombre,''), ' ', COALESCE(v.apellidos,'')))) LIKE UPPER(?)", parametros,
                    "%" + criteria.getVeterinarioNombreLike() + "%");
            SQLUtils.addClause(criteria.getSemillaId(), condiciones, "e.semilla_id = ?", parametros, criteria.getSemillaId());
            SQLUtils.addClause(criteria.getDosisId(), condiciones, "e.dosis_id = ?", parametros, criteria.getDosisId());
            SQLUtils.addClause(criteria.getTratamientoId(), condiciones, "e.tratamiento_id = ?", parametros, criteria.getTratamientoId());

            if (criteria.getPrecioEventoDesde() != null) {
                SQLUtils.addClause(criteria.getPrecioEventoDesde(), condiciones, "e.precio_evento >= ?", parametros, criteria.getPrecioEventoDesde());
            }
            if (criteria.getPrecioEventoHasta() != null) {
                SQLUtils.addClause(criteria.getPrecioEventoHasta(), condiciones, "e.precio_evento <= ?", parametros, criteria.getPrecioEventoHasta());
            }
            if (criteria.getFechaDesde() != null) {
                SQLUtils.addClause(criteria.getFechaDesde(), condiciones, "e.fecha_hora >= ?", parametros, Timestamp.valueOf(criteria.getFechaDesde()));
            }
            if (criteria.getFechaHasta() != null) {
                SQLUtils.addClause(criteria.getFechaHasta(), condiciones, "e.fecha_hora <= ?", parametros, Timestamp.valueOf(criteria.getFechaHasta()));
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY e.fecha_hora DESC");

            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, parametros);
            rs = ps.executeQuery();

            List<EventoDTO> results = new ArrayList<EventoDTO>();
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

    public List<EventoDTO> findByAnimalId(Long animalId) {
        EventoCriteria criteria = new EventoCriteria();
        criteria.setAnimalId(animalId);
        return findBy(criteria);
    }

    public List<EventoDTO> getAll() {
        return findBy(new EventoCriteria());
    }

    public Long create(Evento entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "INSERT INTO evento (animal_id, tipo_evento_id, veterinario_id, fecha_hora, semilla_id, precio_evento, dosis_id, tratamiento_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Timestamp ts = entity.getFechaHora() == null ? null : Timestamp.valueOf(entity.getFechaHora());
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getTipoEventoId(), entity.getVeterinarioId(), ts,
                    entity.getSemillaId(), entity.getPrecioEvento(), entity.getDosisId(), entity.getTratamientoId());
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

    public void update(Evento entity) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            String sql = "UPDATE evento SET animal_id = ?, tipo_evento_id = ?, veterinario_id = ?, fecha_hora = ?, semilla_id = ?, precio_evento = ?, dosis_id = ?, tratamiento_id = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            Timestamp ts = entity.getFechaHora() == null ? null : Timestamp.valueOf(entity.getFechaHora());
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getTipoEventoId(), entity.getVeterinarioId(), ts,
                    entity.getSemillaId(), entity.getPrecioEvento(), entity.getDosisId(), entity.getTratamientoId(), entity.getId());
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
            String sql = "DELETE FROM evento WHERE id = ?";
            ps = c.prepareStatement(sql);
            DAOUtils.setParameters(ps, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }
    }

    private EventoDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        EventoDTO dto = new EventoDTO();
        dto.setId(rs.getLong(i++));
        dto.setAnimalId(rs.getLong(i++));
        dto.setAnimalCrotal(rs.getString(i++));
        dto.setAnimalNombre(rs.getString(i++));
        dto.setTipoEventoId(rs.getLong(i++));
        dto.setTipoEventoNombre(rs.getString(i++));
        dto.setVeterinarioId((Long) rs.getObject(i++));
        dto.setVeterinarioNombreCompleto(rs.getString(i++));
        Timestamp ts = rs.getTimestamp(i++);
        dto.setFechaHora(ts == null ? null : ts.toLocalDateTime());
        dto.setSemillaId((Long) rs.getObject(i++));
        dto.setSemillaCodigo(rs.getString(i++));
        dto.setPrecioEvento((Integer) rs.getObject(i++));
        dto.setDosisId((Long) rs.getObject(i++));
        dto.setDosisNumOrden((Integer) rs.getObject(i++));
        dto.setTratamientoId((Long) rs.getObject(i++));
        dto.setTratamientoNombre(rs.getString(i++));
        return dto;
    }
}

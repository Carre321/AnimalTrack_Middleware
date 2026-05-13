package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;

public class EventoDAO {

	private static Logger logger = LogManager.getLogger(EventoDAO.class.getName());

    private static final String BASE_QUERY =
            "SELECT e.id, e.animal_id, a.crotal, a.nombre, e.tipo_evento_id, te.codigo, te.nombre, " +
            "e.veterinario_id, TRIM(CONCAT(COALESCE(v.nombre,''), ' ', COALESCE(v.apellidos,''))), " +
            "e.fecha_hora, e.semilla_id, s.codigo, e.precio_evento, e.dosis_id, d.num_orden_dosis, " +
            "e.tratamiento_id, t.nombre, e.resultado, e.observaciones " +
            "FROM evento e " +
            "INNER JOIN animal a ON e.animal_id = a.id " +
            "INNER JOIN tipo_evento te ON e.tipo_evento_id = te.id " +
            "LEFT JOIN veterinario v ON e.veterinario_id = v.id " +
            "LEFT JOIN semilla s ON e.semilla_id = s.id " +
            "LEFT JOIN dosis d ON e.dosis_id = d.id " +
            "LEFT JOIN tratamiento t ON e.tratamiento_id = t.id ";

    public EventoDAO() {
    }

    public EventoDTO findById(Connection c, Long id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = BASE_QUERY + " WHERE e.id = ?";
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

    public List<EventoDTO> findBy(Connection c, EventoCriteria criteria) throws Exception {
        Results<EventoDTO> results = findBy(c, criteria, 1, Integer.MAX_VALUE);
        return results == null ? null : results.getPageResults();
    }

    public Results<EventoDTO> findBy(Connection c, EventoCriteria criteria, int from, int pageSize) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

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
            SQLUtils.addClause(criteria.getTipoEventoCodigo(), condiciones, "te.codigo = ?", parametros, criteria.getTipoEventoCodigo());
            SQLUtils.addClause(criteria.getTipoEventoNombreLike(), condiciones, "UPPER(te.nombre) LIKE UPPER(?)", parametros,
                    "%" + criteria.getTipoEventoNombreLike() + "%");
            SQLUtils.addClause(criteria.getVeterinarioId(), condiciones, "e.veterinario_id = ?", parametros, criteria.getVeterinarioId());
            SQLUtils.addClause(criteria.getVeterinarioNombreLike(), condiciones,
                    "UPPER(TRIM(CONCAT(COALESCE(v.nombre,''), ' ', COALESCE(v.apellidos,'')))) LIKE UPPER(?)", parametros,
                    "%" + criteria.getVeterinarioNombreLike() + "%");
            SQLUtils.addClause(criteria.getSemillaId(), condiciones, "e.semilla_id = ?", parametros, criteria.getSemillaId());
            SQLUtils.addClause(criteria.getDosisId(), condiciones, "e.dosis_id = ?", parametros, criteria.getDosisId());
            SQLUtils.addClause(criteria.getTratamientoId(), condiciones, "e.tratamiento_id = ?", parametros, criteria.getTratamientoId());
            SQLUtils.addClause(criteria.getResultado(), condiciones, "e.resultado = ?", parametros, criteria.getResultado());

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

            ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            DAOUtils.setParameters(ps, parametros);
            rs = ps.executeQuery();
            List<EventoDTO> paginaResultados = new ArrayList<EventoDTO>();

            if (from < 1) {
                from = 1;
            }
            if (pageSize <= 0) {
                pageSize = Integer.MAX_VALUE;
            }

            if (rs.absolute(from)) {
                int count = 0;
                do {
                    paginaResultados.add(loadNext(rs));
                    ++count;
                } while (count < pageSize && rs.next());
            }

            int totalResults = SQLUtils.getTotalRows(rs);

            Results<EventoDTO> results = new Results<EventoDTO>();
            results.setPageResults(paginaResultados);
            results.setTotal(totalResults);
            return results;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        throw e;
        } finally {
            JDBCUtils.close(rs, ps);
        }
    }

    public List<EventoDTO> findByAnimalId(Connection c, Long animalId) throws Exception {
        EventoCriteria criteria = new EventoCriteria();
        criteria.setAnimalId(animalId);
        return findBy(c, criteria);
    }

    public List<EventoDTO> getAll(Connection c) throws Exception {
        return findBy(c, new EventoCriteria());
    }

    public Long create(Connection c, Evento entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO evento (animal_id, tipo_evento_id, veterinario_id, fecha_hora, semilla_id, precio_evento, dosis_id, tratamiento_id, resultado, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Timestamp ts = entity.getFechaHora() == null ? null : Timestamp.valueOf(entity.getFechaHora());
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getTipoEventoId(), entity.getVeterinarioId(), ts,
                    entity.getSemillaId(), entity.getPrecioEvento(), entity.getDosisId(), entity.getTratamientoId(),
                    entity.getResultado(), entity.getObservaciones());
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

    public boolean update(Connection c, Evento entity) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE evento SET animal_id = ?, tipo_evento_id = ?, veterinario_id = ?, fecha_hora = ?, semilla_id = ?, precio_evento = ?, dosis_id = ?, tratamiento_id = ?, resultado = ?, observaciones = ? WHERE id = ?";
            ps = c.prepareStatement(sql);
            Timestamp ts = entity.getFechaHora() == null ? null : Timestamp.valueOf(entity.getFechaHora());
            DAOUtils.setParameters(ps, entity.getAnimalId(), entity.getTipoEventoId(), entity.getVeterinarioId(), ts,
                    entity.getSemillaId(), entity.getPrecioEvento(), entity.getDosisId(), entity.getTratamientoId(),
                    entity.getResultado(), entity.getObservaciones(), entity.getId());
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
            String sql = "DELETE FROM evento WHERE id = ?";
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

    private EventoDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        EventoDTO dto = new EventoDTO();
        dto.setId(rs.getLong(i++));
        dto.setAnimalId(rs.getLong(i++));
        dto.setAnimalCrotal(rs.getString(i++));
        dto.setAnimalNombre(rs.getString(i++));
        dto.setTipoEventoId(rs.getLong(i++));
        dto.setTipoEventoCodigo(rs.getString(i++));
        dto.setTipoEventoNombre(rs.getString(i++));
        dto.setVeterinarioId((Long) rs.getObject(i++));
        dto.setVeterinarioNombreCompleto(rs.getString(i++));
        Timestamp ts = rs.getTimestamp(i++);
        dto.setFechaHora(ts == null ? null : ts.toLocalDateTime());
        dto.setSemillaId((Long) rs.getObject(i++));
        dto.setSemillaCodigo(rs.getString(i++));
        dto.setPrecioEvento(rs.getBigDecimal(i++));
        dto.setDosisId((Long) rs.getObject(i++));
        dto.setDosisNumOrden((Integer) rs.getObject(i++));
        dto.setTratamientoId((Long) rs.getObject(i++));
        dto.setTratamientoNombre(rs.getString(i++));
        dto.setResultado(rs.getString(i++));
        dto.setObservaciones(rs.getString(i++));
        return dto;
    }
}

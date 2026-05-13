package com.tonin.animaltrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tonin.animaltrack.dao.criteria.AnimalCriteria;
import com.tonin.animaltrack.dao.utils.DAOUtils;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.dao.utils.SQLUtils;
import com.tonin.animaltrack.model.Animal;
import com.tonin.animaltrack.model.dto.AnimalDTO;

public class AnimalDAO {

	private static Logger logger = LogManager.getLogger(AnimalDAO.class.getName());

	private static final String BASE_QUERY =
			"SELECT a.id, a.crotal, a.nombre, a.fecha_nacimiento, a.fecha_baja, a.granja_id, g.nombre, a.sexo_id, s.nombre, " +
					"a.raza_id, r.nombre, a.madre_interna_id, a.madre_externa_crotal, a.padre_interno_id, p.nombre, a.event_parto_id " +
					"FROM animal a " +
					"INNER JOIN granja g ON a.granja_id = g.id " +
					"INNER JOIN sexo s ON a.sexo_id = s.id " +
					"LEFT JOIN raza r ON a.raza_id = r.id " +
					"LEFT JOIN animal p ON a.padre_interno_id = p.id ";

	public AnimalDAO() {
	}

	public AnimalDTO findById(Connection c, Long id) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE a.id = ? ");

			ps = c.prepareStatement(sql.toString());
			DAOUtils.setParameters(ps, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return loadNext(rs);
			}
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(rs, ps);
		}
	}

	public AnimalDTO findByCrotal(Connection c, String crotal) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE a.crotal = ? ");

			ps = c.prepareStatement(sql.toString());
			DAOUtils.setParameters(ps, crotal);
			rs = ps.executeQuery();

			if (rs.next()) {
				return loadNext(rs);
			}
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(rs, ps);
		}
	}

	public Results<AnimalDTO> findBy(Connection c, AnimalCriteria criteria, int from, int pageSize) throws Exception {


		if (logger.isInfoEnabled()) {
			logger.info("Criteria: {}", criteria); //Muy importante usarlos de esta manera para evitar problemas de rendimiento al usar el +. ("Criteria" +  criteria)
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<String>();
			List<Object> parametros = new ArrayList<Object>();

			SQLUtils.addClause(criteria.getGranjaId(), condiciones, "a.granja_id = ?", parametros, criteria.getGranjaId());
			SQLUtils.addClause(criteria.getSexoId(), condiciones, "a.sexo_id = ?", parametros, criteria.getSexoId());
			SQLUtils.addClause(criteria.getRazaId(), condiciones, "a.raza_id = ?", parametros, criteria.getRazaId());
			SQLUtils.addClause(criteria.getCrotal(), condiciones, "a.crotal = ?", parametros, criteria.getCrotal());
			SQLUtils.addClause(criteria.getPadreInternoId(), condiciones, "a.padre_interno_id = ?", parametros, criteria.getPadreInternoId());
			SQLUtils.addClause(criteria.getMadreInternaId(), condiciones, "a.madre_interna_id = ?", parametros, criteria.getMadreInternaId());
			SQLUtils.addClause(criteria.getEventPartoId(), condiciones, "a.event_parto_id = ?", parametros, criteria.getEventPartoId());

			if (criteria.getCrotalLike() != null) {
				SQLUtils.addClause(criteria.getCrotalLike(), condiciones, "UPPER(a.crotal) LIKE UPPER(?)", parametros, "%" + criteria.getCrotalLike() + "%");
			}
			if (criteria.getNombreLike() != null) {
				SQLUtils.addClause(criteria.getNombreLike(), condiciones, "UPPER(a.nombre) LIKE UPPER(?)", parametros, "%" + criteria.getNombreLike() + "%");
			}

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ").append(criteria.getOrderby()).append(criteria.isAscDesc() ? " ASC " : " DESC ");

			logger.info("SQL: {}", sql);

			//            if (logger.isInfoEnabled()) {
			//				logger.info("Criteria SQL: {}: {}:", criteria, sql);
			//			}

			ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			DAOUtils.setParameters(ps, parametros);

			rs = ps.executeQuery();
			List<AnimalDTO> paginaResultados = new ArrayList<AnimalDTO>();

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

			Results<AnimalDTO> results = new Results<AnimalDTO>();
			results.setPageResults(paginaResultados);
			results.setTotal(totalResults);
			return results;

		} catch (Exception e) {
			logger.error(e.getMessage()+ ":" +criteria, e);
			throw e;
		} finally {
			JDBCUtils.close(rs, ps);
		}
	}

	public Long create(Connection c, Animal a) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO animal (nombre, crotal, fecha_nacimiento, fecha_baja, granja_id, raza_id, sexo_id, ");
			sql.append("madre_interna_id, madre_externa_crotal, padre_interno_id, event_parto_id) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = c.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			DAOUtils.setParameters(ps,
					a.getNombre(),
					a.getCrotal(),
					a.getFechaNacimiento(),
					a.getFechaBaja(),
					a.getGranjaId(),
					a.getRazaId(),
					a.getSexoId(),
					a.getMadreInternaId(),
					a.getMadreExternaCrotal(),
					a.getPadreInternoId(),
					a.getEventPartoId());

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

	public boolean update(Connection c, Animal a) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE animal SET nombre = ?, crotal = ?, fecha_nacimiento = ?, fecha_baja = ?, granja_id = ?, raza_id = ?, sexo_id = ?, ");
			sql.append("madre_interna_id = ?, madre_externa_crotal = ?, padre_interno_id = ?, event_parto_id = ? ");
			sql.append("WHERE id = ?");

			ps = c.prepareStatement(sql.toString());

			DAOUtils.setParameters(ps,
					a.getNombre(),
					a.getCrotal(),
					a.getFechaNacimiento(),
					a.getFechaBaja(),
					a.getGranjaId(),
					a.getRazaId(),
					a.getSexoId(),
					a.getMadreInternaId(),
					a.getMadreExternaCrotal(),
					a.getPadreInternoId(),
					a.getEventPartoId(),
					a.getId());

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

			String sql = "DELETE FROM animal WHERE id = ?";
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

	private AnimalDTO loadNext(ResultSet rs) throws Exception {
		int i = 1;

		AnimalDTO a = new AnimalDTO();
		a.setId(rs.getLong(i++));
		a.setCrotal(rs.getString(i++));
		a.setNombre(rs.getString(i++));
		a.setFechaNacimiento(rs.getDate(i++));
		a.setFechaBaja(rs.getDate(i++));

		a.setGranjaId(rs.getLong(i++));
		a.setGranjaNombre(rs.getString(i++));

		a.setSexoId(rs.getLong(i++));
		a.setSexoNombre(rs.getString(i++));

		a.setRazaId((Long) rs.getObject(i++));
		a.setRazaNombre(rs.getString(i++));

		a.setMadreInternaId((Long) rs.getObject(i++));
		a.setMadreExternaCrotal(rs.getString(i++));
		a.setPadreInternoId((Long) rs.getObject(i++));
		a.setPadreInternoNombre(rs.getString(i++));
		a.setEventPartoId((Long) rs.getObject(i++));

		return a;
	}
}

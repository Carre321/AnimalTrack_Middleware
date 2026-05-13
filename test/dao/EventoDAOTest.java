package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import com.tonin.animaltrack.dao.EventoDAO;
import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;

public class EventoDAOTest {

	private static EventoDAO dao = new EventoDAO();
	private static Long createdId = null;

	public static final void testFindById() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
			EventoDTO dto = dao.findById(c, 1L);
			commit = true;
			System.out.println(dto);
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static final void testFindBy() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		EventoCriteria criteria = new EventoCriteria();
		criteria.setAnimalId(1L);
		List<EventoDTO> resultados = dao.findBy(c, criteria);
		commit = true;
		for (EventoDTO dto : resultados) {
			System.out.println(dto);
		}
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static final void testCreate() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		Evento e = new Evento();
		e.setAnimalId(1L);
		e.setTipoEventoId(1L);
		e.setFechaHora(LocalDateTime.now());
		createdId = dao.create(c, e);
		commit = true;
		System.out.println("ID " + createdId);
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static final void testUpdate() throws Exception {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		EventoDTO dto = dao.findById(c, createdId);
		if (dto == null) {
			System.out.println("No existe");
			return;
		}
		Evento e = new Evento();
		e.setId(dto.getId());
		e.setAnimalId(dto.getAnimalId());
		e.setTipoEventoId(dto.getTipoEventoId());
		e.setVeterinarioId(dto.getVeterinarioId());
		e.setFechaHora(dto.getFechaHora());
		e.setSemillaId(dto.getSemillaId());
		e.setPrecioEvento(new BigDecimal("99"));
		e.setDosisId(dto.getDosisId());
		e.setTratamientoId(dto.getTratamientoId());
		dao.update(c, e);
		commit = true;
		System.out.println(dao.findById(c, dto.getId()));
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static void deleteTest() throws Exception {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		dao.delete(c, createdId);
		commit = true;
		System.out.println("Deleted " + createdId);
		createdId = null;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	private static Connection openConnection() throws Exception {
		Connection c = JDBCUtils.getConnection();
		c.setAutoCommit(false);
		return c;
	}

	public static void main(String[] args) throws Exception {
		//testFindById();
		//testFindBy();
		testCreate();
		//testUpdate();
		//deleteTest();
	}
}

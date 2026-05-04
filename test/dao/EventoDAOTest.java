package dao;

import java.time.LocalDateTime;
import java.util.List;

import com.tonin.animaltrack.dao.EventoDAO;
import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;

public class EventoDAOTest {

	private static EventoDAO dao = new EventoDAO();
	private static Long createdId = null;

	public static final void testFindById() {
		EventoDTO dto = dao.findById(1L);
		System.out.println(dto);
	}

	public static final void testFindBy() {
		EventoCriteria criteria = new EventoCriteria();
		criteria.setAnimalId(1L);
		List<EventoDTO> resultados = dao.findBy(criteria);
		for (EventoDTO dto : resultados) {
			System.out.println(dto);
		}
	}

	public static final void testCreate() {
		Evento e = new Evento();
		e.setAnimalId(1L);
		e.setTipoEventoId(1L);
		e.setFechaHora(LocalDateTime.now());
		createdId = dao.create(e);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		EventoDTO dto = dao.findById(createdId);
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
		e.setPrecioEvento(99);
		e.setDosisId(dto.getDosisId());
		e.setTratamientoId(dto.getTratamientoId());
		dao.update(e);
		System.out.println(dao.findById(dto.getId()));
	}

	public static void deleteTest() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		dao.delete(createdId);
		System.out.println("Deleted " + createdId);
		createdId = null;
	}

	public static void main(String[] args) {
		//testFindById();
		//testFindBy();
		testCreate();
		//testUpdate();
		//deleteTest();
	}
}

package dao;

import java.time.LocalDateTime;
import java.util.List;

import com.tonin.animaltrack.dao.NotificacionDAO;
import com.tonin.animaltrack.dao.criteria.NotificacionCriteria;
import com.tonin.animaltrack.model.Notificacion;
import com.tonin.animaltrack.model.dto.NotificacionDTO;

public class NotificacionDAOTest {

	private static NotificacionDAO dao = new NotificacionDAO();
	private static Long createdId = null;

	public static final void testFindById() {
		NotificacionDTO dto = dao.findById(1L);
		System.out.println(dto);
	}

	public static final void testFindBy() {
		NotificacionCriteria criteria = new NotificacionCriteria();
		criteria.setEventoId(1L);
		List<NotificacionDTO> resultados = dao.findBy(criteria);
		for (NotificacionDTO dto : resultados) {
			System.out.println(dto);
		}
	}

	public static final void testCreate() {
		Notificacion n = new Notificacion();
		n.setEventoId(1L);
		n.setTipo("INFO");
		n.setFechaEmision(LocalDateTime.now());
		n.setDescripcion("Notificacion de prueba");
		n.setTipoNotificacionId(1L);
		createdId = dao.create(n);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		NotificacionDTO dto = dao.findById(createdId);
		if (dto == null) {
			System.out.println("No existe");
			return;
		}
		Notificacion n = new Notificacion();
		n.setId(dto.getId());
		n.setEventoId(dto.getEventoId());
		n.setTipo(dto.getTipo());
		n.setFechaEmision(dto.getFechaEmision());
		n.setDescripcion("Notificacion editada");
		n.setTipoNotificacionId(dto.getTipoNotificacionId());
		dao.update(n);
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

package dao;

import java.util.List;

import com.tonin.animaltrack.dao.SemillaDAO;
import com.tonin.animaltrack.model.Semilla;

public class SemillaDAOTest {

	private static Long createdId = null;
	private static SemillaDAO dao = new SemillaDAO();

	public static final void testFindById() {
		Semilla s = dao.findById(1L);
		System.out.println(s);
	}

	public static final void testFindBy() {
		Semilla s = dao.findByCodigo("LIM-2025-01");
		System.out.println(s);
		List<Semilla> all = dao.getAll();
		for (Semilla item : all) {
			System.out.println(item);
		}
	}

	public static final void testCreate() {
		Semilla s = new Semilla();
		s.setCodigo("TEST-" + System.currentTimeMillis());
		s.setDescripcion("Semilla test");
		createdId = dao.create(s);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Semilla s = dao.findById(createdId);
		if (s == null) {
			System.out.println("No existe");
			return;
		}
		s.setDescripcion("Semilla editada");
		dao.update(s);
		System.out.println(dao.findById(createdId));
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
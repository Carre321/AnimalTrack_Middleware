package dao;

import java.util.List;

import com.tonin.animaltrack.dao.TratamientoDAO;
import com.tonin.animaltrack.model.Tratamiento;

public class TratamientoDAOTest {

	private static Long createdId = null;
	private static TratamientoDAO dao = new TratamientoDAO();

	public static final void testFindById() {
		Tratamiento e = dao.findById(1L);
		System.out.println(e);
	}

	public static final void testFindBy() {
		List<Tratamiento> resultados = dao.getAll();
		for (Tratamiento e : resultados) {
			System.out.println(e);
		}
	}

	public static final void testCreate() {
		Tratamiento e = new Tratamiento();
		e.setNombre("TEST-TRATAMIENTO-" + System.currentTimeMillis());
		createdId = dao.create(e);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Tratamiento e = dao.findById(createdId);
		if (e == null) {
			System.out.println("No existe");
			return;
		}
		e.setNombre(e.getNombre() + "-UPD");
		dao.update(e);
		System.out.println(dao.findById(createdId));
	}

	public static final void deleteTest() {
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
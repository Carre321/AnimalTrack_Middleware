package dao;

import java.util.List;

import com.tonin.animaltrack.dao.DosisDAO;
import com.tonin.animaltrack.model.Dosis;

public class DosisDAOTest {

	private static Long createdId = null;
	private static DosisDAO dao = new DosisDAO();

	public static final void testFindById() {
		Dosis d = dao.findById(1L);
		System.out.println(d);
	}

	public static final void testFindBy() {
		List<Dosis> resultados = dao.findByTratamientoId(1L);
		for (Dosis d : resultados) {
			System.out.println(d);
		}
	}

	public static final void testCreate() {
		Dosis d = new Dosis();
		d.setPlazoSiguiente(30);
		d.setNumOrdenDosis(9);
		d.setTratamientoId(1L);
		createdId = dao.create(d);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Dosis d = dao.findById(createdId);
		if (d == null) {
			System.out.println("No existe");
			return;
		}
		d.setPlazoSiguiente(45);
		dao.update(d);
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
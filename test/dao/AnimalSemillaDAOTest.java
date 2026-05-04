package dao;

import java.util.List;

import com.tonin.animaltrack.dao.AnimalSemillaDAO;
import com.tonin.animaltrack.model.AnimalSemilla;

public class AnimalSemillaDAOTest {

	private static Long oldAnimalId = null;
	private static Long oldSemillaId = null;
	private static AnimalSemillaDAO dao = new AnimalSemillaDAO();

	public static final void testFindById() {
		List<AnimalSemilla> resultados = dao.findByAnimalId(1L);
		for (AnimalSemilla e : resultados) {
			System.out.println(e);
		}
	}

	public static final void testFindBy() {
		List<AnimalSemilla> resultados = dao.findBySemillaId(1L);
		for (AnimalSemilla e : resultados) {
			System.out.println(e);
		}
	}

	public static final void testCreate() {
		AnimalSemilla e = new AnimalSemilla();
		e.setAnimalId(1L);
		e.setSemillaId(2L);
		dao.create(e);
		oldAnimalId = e.getAnimalId();
		oldSemillaId = e.getSemillaId();
		System.out.println("Created " + oldAnimalId + "-" + oldSemillaId);
	}

	public static final void testUpdate() {
		if (oldAnimalId == null || oldSemillaId == null) {
			System.out.println("No hay clave creada");
			return;
		}
		AnimalSemilla e = new AnimalSemilla();
		e.setAnimalId(oldAnimalId);
		e.setSemillaId(3L);
		dao.update(oldAnimalId, oldSemillaId, e);
		oldSemillaId = 3L;
		System.out.println("Updated " + oldAnimalId + "-" + oldSemillaId);
	}

	public static void deleteTest() {
		if (oldAnimalId == null || oldSemillaId == null) {
			System.out.println("No hay clave creada");
			return;
		}
		dao.delete(oldAnimalId, oldSemillaId);
		System.out.println("Deleted " + oldAnimalId + "-" + oldSemillaId);
		oldAnimalId = null;
		oldSemillaId = null;
	}

	public static void main(String[] args) {
		//testFindById();
		//testFindBy();
		testCreate();
		//testUpdate();
		//deleteTest();
	}
}
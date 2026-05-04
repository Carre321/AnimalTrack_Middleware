package dao;

import java.util.List;

import com.tonin.animaltrack.dao.VeterinarioGranjaDAO;
import com.tonin.animaltrack.model.VeterinarioGranja;

public class VeterinarioGranjaDAOTest {

	private static Long oldVetId = null;
	private static Long oldGranjaId = null;
	private static VeterinarioGranjaDAO dao = new VeterinarioGranjaDAO();

	public static final void testFindById() {
		List<VeterinarioGranja> resultados = dao.findByVeterinarioId(1L);
		for (VeterinarioGranja e : resultados) {
			System.out.println(e);
		}
	}

	public static final void testFindBy() {
		List<VeterinarioGranja> resultados = dao.findByGranjaId(1L);
		for (VeterinarioGranja e : resultados) {
			System.out.println(e);
		}
	}

	public static final void testCreate() {
		VeterinarioGranja e = new VeterinarioGranja();
		e.setVeterinarioId(1L);
		e.setGranjaId(1L);
		dao.create(e);
		oldVetId = e.getVeterinarioId();
		oldGranjaId = e.getGranjaId();
		System.out.println("Created " + oldVetId + "-" + oldGranjaId);
	}

	public static final void testUpdate() {
		if (oldVetId == null || oldGranjaId == null) {
			System.out.println("No hay clave creada");
			return;
		}
		VeterinarioGranja e = new VeterinarioGranja();
		e.setVeterinarioId(2L);
		e.setGranjaId(oldGranjaId);
		dao.update(oldVetId, oldGranjaId, e);
		oldVetId = 2L;
		System.out.println("Updated " + oldVetId + "-" + oldGranjaId);
	}

	public static void deleteTest() {
		if (oldVetId == null || oldGranjaId == null) {
			System.out.println("No hay clave creada");
			return;
		}
		dao.delete(oldVetId, oldGranjaId);
		System.out.println("Deleted " + oldVetId + "-" + oldGranjaId);
		oldVetId = null;
		oldGranjaId = null;
	}

	public static void main(String[] args) {
		//testFindById();
		//testFindBy();
		testCreate();
		//testUpdate();
		//deleteTest();
	}
}
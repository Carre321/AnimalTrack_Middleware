package dao;

import java.util.List;

import com.tonin.animaltrack.dao.ProvinciaDAO;
import com.tonin.animaltrack.model.Provincia;

public class ProvinciaDAOTest {

	private static ProvinciaDAO dao = new ProvinciaDAO();

	public static final void testFindById() {
		Provincia e = dao.findById(1L);
		System.out.println(e);
	}

	public static final void testFindBy() {
		List<Provincia> resultados = dao.getAll();
		for (Provincia e : resultados) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		testFindById();
		//testFindBy();
	}
}

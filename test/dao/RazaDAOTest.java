package dao;

import java.util.List;

import com.tonin.animaltrack.dao.RazaDAO;
import com.tonin.animaltrack.model.Raza;

public class RazaDAOTest {

	private static RazaDAO dao = new RazaDAO();

	public static final void testFindById() {
		Raza e = dao.findById(1L);
		System.out.println(e);
	}

	public static final void testFindBy() {
		List<Raza> resultados = dao.getAll();
		for (Raza e : resultados) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		testFindById();
		//testFindBy();
	}
}

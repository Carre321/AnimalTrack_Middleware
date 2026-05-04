package dao;

import java.util.List;

import com.tonin.animaltrack.dao.SexoDAO;
import com.tonin.animaltrack.model.Sexo;

public class SexoDAOTest {

	private static SexoDAO dao = new SexoDAO();

	public static final void testFindById() {
		Sexo e = dao.findById(1L);
		System.out.println(e);
	}

	public static final void testFindBy() {
		List<Sexo> resultados = dao.getAll();
		for (Sexo e : resultados) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		testFindById();
		//testFindBy();
	}
}

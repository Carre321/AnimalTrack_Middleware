package dao;

import java.util.List;

import com.tonin.animaltrack.dao.MunicipioDAO;
import com.tonin.animaltrack.model.Municipio;

public class MunicipioDAOTest {

	private static MunicipioDAO dao = new MunicipioDAO();

	public static final void testFindById() {
		Municipio m = dao.findById(1L);
		System.out.println(m);
	}

	public static final void testFindBy() {
		List<Municipio> resultados = dao.findByProvinciaId(1L);
		for (Municipio m : resultados) {
			System.out.println(m);
		}
	}

	public static void main(String[] args) {
		testFindById();
		//testFindBy();
	}
}

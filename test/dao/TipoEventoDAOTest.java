package dao;

import java.util.List;

import com.tonin.animaltrack.dao.TipoEventoDAO;
import com.tonin.animaltrack.model.TipoEvento;

public class TipoEventoDAOTest {

	private static TipoEventoDAO dao = new TipoEventoDAO();

	public static final void testFindById() {
		TipoEvento e = dao.findById(1L);
		System.out.println(e);
	}

	public static final void testFindBy() {
		List<TipoEvento> resultados = dao.getAll();
		for (TipoEvento e : resultados) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		testFindById();
		//testFindBy();
	}
}

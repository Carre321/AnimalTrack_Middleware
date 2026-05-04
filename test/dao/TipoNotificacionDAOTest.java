package dao;

import java.util.List;

import com.tonin.animaltrack.dao.TipoNotificacionDAO;
import com.tonin.animaltrack.model.TipoNotificacion;

public class TipoNotificacionDAOTest {

	private static TipoNotificacionDAO dao = new TipoNotificacionDAO();

	public static final void testFindById() {
		TipoNotificacion e = dao.findById(1L);
		System.out.println(e);
	}

	public static final void testFindBy() {
		List<TipoNotificacion> resultados = dao.getAll();
		for (TipoNotificacion e : resultados) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		testFindById();
		//testFindBy();
	}
}

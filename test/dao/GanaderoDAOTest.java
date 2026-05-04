package dao;

import java.util.List;

import com.tonin.animaltrack.dao.GanaderoDAO;
import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;

public class GanaderoDAOTest {

	private static GanaderoDAO dao = new GanaderoDAO();
	private static Long createdId = null;

	public static final void testFindById() {
		GanaderoDTO dto = dao.findById(1L);
		System.out.println(dto);
	}

	public static final void testFindBy() {
		GanaderoCriteria criteria = new GanaderoCriteria();
		criteria.setMunicipioId(1L);
		List<GanaderoDTO> resultados = dao.findBy(criteria);
		for (GanaderoDTO dto : resultados) {
			System.out.println(dto);
		}
	}

	public static final void testCreate() {
		Ganadero g = new Ganadero();
		g.setDni("99999999X");
		g.setNombre("TEST");
		g.setApellidos("GANADERO");
		g.setTelefono("600000000");
		g.setEmail("test.ganadero@local");
		g.setMunicipioId(1L);
		createdId = dao.create(g);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		GanaderoDTO dto = dao.findById(createdId);
		if (dto == null) {
			System.out.println("No existe");
			return;
		}
		Ganadero g = new Ganadero();
		g.setId(dto.getId());
		g.setDni(dto.getDni());
		g.setNombre(dto.getNombre());
		g.setApellidos(dto.getApellidos());
		g.setTelefono("611111111");
		g.setEmail(dto.getEmail());
		g.setMunicipioId(dto.getMunicipioId());
		dao.update(g);
		System.out.println(dao.findById(dto.getId()));
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

package dao;

import java.util.List;

import com.tonin.animaltrack.dao.GranjaDAO;
import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;

public class GranjaDAOTest {

	private static GranjaDAO dao = new GranjaDAO();
	private static Long createdId = null;

	public static final void testFindById() {
		GranjaDTO dto = dao.findById(1L);
		System.out.println(dto);
	}

	public static final void testFindBy() {
		GranjaCriteria criteria = new GranjaCriteria();
		criteria.setGanaderoId(1L);
		List<GranjaDTO> resultados = dao.findBy(criteria);
		for (GranjaDTO dto : resultados) {
			System.out.println(dto);
		}
	}

	public static final void testCreate() {
		Granja g = new Granja();
		g.setNombre("TEST-GRANJA-" + System.currentTimeMillis());
		g.setDireccion("Direccion de prueba");
		g.setMunicipioId(1L);
		g.setGanaderoId(1L);
		createdId = dao.create(g);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		GranjaDTO dto = dao.findById(createdId);
		if (dto == null) {
			System.out.println("No existe");
			return;
		}
		Granja g = new Granja();
		g.setId(dto.getId());
		g.setNombre(dto.getNombre());
		g.setDireccion("Direccion editada");
		g.setMunicipioId(dto.getMunicipioId());
		g.setGanaderoId(dto.getGanaderoId());
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

package dao;

import java.sql.Date;
import java.util.List;

import com.tonin.animaltrack.dao.AnimalDAO;
import com.tonin.animaltrack.dao.Results;
import com.tonin.animaltrack.dao.criteria.AnimalCriteria;
import com.tonin.animaltrack.model.Animal;
import com.tonin.animaltrack.model.dto.AnimalDTO;
import com.tonin.animaltrack.service.AnimalService;
import com.tonin.animaltrack.service.impl.AnimalServiceImpl;

public class AnimalDAOTest {

	private static Long createdId = null;
	private static AnimalDAO dao = new AnimalDAO();
	private static AnimalService service = new AnimalServiceImpl();

	public static final void testFindById() {
		AnimalDTO a = dao.findById(1L);
		System.out.println(a);
	}

	public static final void testFindBy() {
		AnimalCriteria criteria = new AnimalCriteria();
		criteria.setGranjaId(1L);
		criteria.setCrotalLike("ES270");
		Results<AnimalDTO> results = dao.findBy(criteria, 1, Integer.MAX_VALUE);
		List<AnimalDTO> resultados = results == null ? null : results.getPageResults();
		if (resultados == null) {
			return;
		}
		for (AnimalDTO a : resultados) {
			System.out.println(a);
		}
	}

	public static final void testCreate() {
		Animal a = new Animal();
		a.setNombre("PRUEBA");
		a.setCrotal("TEST-" + System.currentTimeMillis());
		a.setFechaNacimiento(Date.valueOf("2024-01-15"));
		a.setFechaBaja(null);
		a.setGranjaId(1L);
		a.setSexoId(2L);
		createdId = dao.create(a);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		AnimalDTO dto = dao.findById(createdId);
		if (dto == null) {
			System.out.println("No existe");
			return;
		}
		Animal a = new Animal();
		a.setId(dto.getId());
		a.setNombre("PRUEBA EDITADA");
		a.setCrotal(dto.getCrotal());
		a.setFechaNacimiento(dto.getFechaNacimiento());
		a.setFechaBaja(dto.getFechaBaja());
		a.setGranjaId(dto.getGranjaId());
		a.setRazaId(dto.getRazaId());
		a.setSexoId(dto.getSexoId());
		a.setMadreInternaId(dto.getMadreInternaId());
		a.setMadreExternaCrotal(dto.getMadreExternaCrotal());
		a.setPadreInternoId(dto.getPadreInternoId());
		a.setEventPartoId(dto.getEventPartoId());
		dao.update(a);
		System.out.println(dao.findById(createdId));
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

	public static void testPagedFindBy() {
		AnimalCriteria criteria = new AnimalCriteria();
		int pageSize = 10;
		Results<AnimalDTO> results = null;
		List<AnimalDTO> resultsPage = null;
		int from = 1;
		do {
			results = service.findByCriteria(criteria, from, pageSize);
			resultsPage = results == null ? null : results.getPageResults();
			print(resultsPage);
			from = from + pageSize;
		} while (resultsPage != null && resultsPage.size()==pageSize);
	}

	private static void print(List<AnimalDTO> resultsPage) {
		System.out.println("Imprimiendo paginas...");
		if (resultsPage == null) {
			return;
		}
		for (AnimalDTO animal: resultsPage) {
			System.out.println(animal);
		}  
		
	}

	public static void main(String[] args) {
		//testFindById();
		testPagedFindBy();
		//testCreate();
		//testUpdate();
		//deleteTest();
	}
}

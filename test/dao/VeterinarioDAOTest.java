package dao;

import java.util.List;

import com.tonin.animaltrack.dao.VeterinarioDAO;
import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;

public class VeterinarioDAOTest {

	private static VeterinarioDAO dao = new VeterinarioDAO();
	private static Long createdId = null;

	public static final void testFindById() {
		VeterinarioDTO dto = dao.findById(1L);
		System.out.println(dto);
	}

	public static final void testFindBy() {
		VeterinarioCriteria criteria = new VeterinarioCriteria();
		criteria.setMunicipioId(1L);
		List<VeterinarioDTO> resultados = dao.findBy(criteria);
		for (VeterinarioDTO dto : resultados) {
			System.out.println(dto);
		}
	}

	public static final void testCreate() {
		Veterinario v = new Veterinario();
		v.setCodigo("VET-T-" + System.currentTimeMillis());
		v.setDni("88888888W");
		v.setNombre("TEST");
		v.setApellidos("VETERINARIO");
		v.setTelefono("622222222");
		v.setEmail("test.vet@local");
		v.setMunicipioId(1L);
		createdId = dao.create(v);
		System.out.println("ID " + createdId);
	}

	public static final void testUpdate() {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		VeterinarioDTO dto = dao.findById(createdId);
		if (dto == null) {
			System.out.println("No existe");
			return;
		}
		Veterinario v = new Veterinario();
		v.setId(dto.getId());
		v.setCodigo(dto.getCodigo());
		v.setDni(dto.getDni());
		v.setNombre(dto.getNombre());
		v.setApellidos(dto.getApellidos());
		v.setTelefono("633333333");
		v.setEmail(dto.getEmail());
		v.setMunicipioId(dto.getMunicipioId());
		dao.update(v);
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

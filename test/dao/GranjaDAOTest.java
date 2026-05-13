package dao;

import java.sql.Connection;
import java.util.List;

import com.tonin.animaltrack.dao.GranjaDAO;
import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;

public class GranjaDAOTest {

	private static GranjaDAO dao = new GranjaDAO();
	private static Long createdId = null;

	public static final void testFindById() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
			GranjaDTO dto = dao.findById(c, 1L);
			commit = true;
			System.out.println(dto);
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static final void testFindBy() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		GranjaCriteria criteria = new GranjaCriteria();
		criteria.setGanaderoId(1L);
		List<GranjaDTO> resultados = dao.findBy(c, criteria);
		commit = true;
		for (GranjaDTO dto : resultados) {
			System.out.println(dto);
		}
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static final void testCreate() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		Granja g = new Granja();
		g.setNombre("TEST-GRANJA-" + System.currentTimeMillis());
		g.setDireccion("Direccion de prueba");
		g.setMunicipioId(1L);
		g.setGanaderoId(1L);
		createdId = dao.create(c, g);
		commit = true;
		System.out.println("ID " + createdId);
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static final void testUpdate() throws Exception {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		GranjaDTO dto = dao.findById(c, createdId);
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
		dao.update(c, g);
		commit = true;
		System.out.println(dao.findById(c, dto.getId()));
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	public static void deleteTest() throws Exception {
		if (createdId == null) {
			System.out.println("No hay ID creado");
			return;
		}
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
		dao.delete(c, createdId);
		commit = true;
		System.out.println("Deleted " + createdId);
		createdId = null;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	private static Connection openConnection() throws Exception {
		Connection c = JDBCUtils.getConnection();
		c.setAutoCommit(false);
		return c;
	}

	public static void main(String[] args) throws Exception {
		//testFindById();
		//testFindBy();
		testCreate();
		//testUpdate();
		//deleteTest();
	}
}

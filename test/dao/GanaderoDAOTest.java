package dao;

import java.sql.Connection;
import java.util.List;

import com.tonin.animaltrack.dao.GanaderoDAO;
import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.dao.utils.JDBCUtils;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;

public class GanaderoDAOTest {

	private static GanaderoDAO dao = new GanaderoDAO();
	private static Long createdId = null;

	public static final void testFindById() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = openConnection();
			GanaderoDTO dto = dao.findById(c, 1L);
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
		GanaderoCriteria criteria = new GanaderoCriteria();
		criteria.setMunicipioId(1L);
		List<GanaderoDTO> resultados = dao.findBy(c, criteria);
		commit = true;
		for (GanaderoDTO dto : resultados) {
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
		Ganadero g = new Ganadero();
		g.setDni("99999999X");
		g.setNombre("TEST");
		g.setApellidos("GANADERO");
		g.setTelefono("600000000");
		g.setEmail("test.ganadero@local");
		g.setMunicipioId(1L);
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
		GanaderoDTO dto = dao.findById(c, createdId);
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

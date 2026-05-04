package service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;
import com.tonin.animaltrack.service.GanaderoService;
import com.tonin.animaltrack.service.impl.GanaderoServiceImpl;

public class GanaderoServiceTest {

    private GanaderoService service = null;
    private GanaderoDTO created = null;

    public GanaderoServiceTest() {
        this.service = new GanaderoServiceImpl();
    }

    public void testFindById(Long id) {
        GanaderoDTO dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindBy() {
        GanaderoCriteria criteria = new GanaderoCriteria();
        criteria.setMunicipioId(1L);
        List<GanaderoDTO> resultados = service.findByCriteria(criteria);
        for (GanaderoDTO dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() {
        Ganadero g = new Ganadero();
        g.setDni("99999999X");
        g.setNombre("TEST");
        g.setApellidos("GANADERO");
        g.setTelefono("600000000");
        g.setEmail("test.ganadero@local");
        g.setMunicipioId(1L);
        created = service.create(g);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        Ganadero g = new Ganadero();
        g.setId(created.getId());
        g.setDni(created.getDni());
        g.setNombre(created.getNombre());
        g.setApellidos(created.getApellidos());
        g.setTelefono("611111111");
        g.setEmail(created.getEmail());
        g.setMunicipioId(created.getMunicipioId());
        service.update(g);
        System.out.println(service.findById(created.getId()));
    }

    public void deleteTest() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        service.delete(created.getId());
        System.out.println("Deleted " + created.getId());
        created = null;
    }

    public static void main(String[] args) {
        GanaderoServiceTest test = new GanaderoServiceTest();
        test.testFindById(1L);
//      test.testFindBy();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

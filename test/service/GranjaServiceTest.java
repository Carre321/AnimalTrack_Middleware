package service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;
import com.tonin.animaltrack.service.GranjaService;
import com.tonin.animaltrack.service.impl.GranjaServiceImpl;

public class GranjaServiceTest {

    private GranjaService service = null;
    private GranjaDTO created = null;

    public GranjaServiceTest() {
        this.service = new GranjaServiceImpl();
    }

    public void testFindById(Long id) {
        GranjaDTO dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindBy() {
        GranjaCriteria criteria = new GranjaCriteria();
        criteria.setGanaderoId(1L);
        List<GranjaDTO> resultados = service.findByCriteria(criteria);
        for (GranjaDTO dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() {
        Granja g = new Granja();
        g.setNombre("TEST-GRANJA-" + System.currentTimeMillis());
        g.setDireccion("Direccion de prueba");
        g.setMunicipioId(1L);
        g.setGanaderoId(1L);
        created = service.create(g);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        Granja g = new Granja();
        g.setId(created.getId());
        g.setNombre(created.getNombre());
        g.setDireccion("Direccion editada");
        g.setMunicipioId(created.getMunicipioId());
        g.setGanaderoId(created.getGanaderoId());
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
        GranjaServiceTest test = new GranjaServiceTest();
        test.testFindById(1L);
//      test.testFindBy();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

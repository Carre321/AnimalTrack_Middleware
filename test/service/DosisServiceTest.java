package service;

import java.util.List;

import com.tonin.animaltrack.model.Dosis;
import com.tonin.animaltrack.service.DosisService;
import com.tonin.animaltrack.service.impl.DosisServiceImpl;

public class DosisServiceTest {

    private DosisService service = null;
    private Dosis created = null;

    public DosisServiceTest() {
        this.service = new DosisServiceImpl();
    }

    public void testFindById(Long id) {
        Dosis dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindByTratamientoId() {
        List<Dosis> resultados = service.findByTratamientoId(1L);
        for (Dosis dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() {
        Dosis d = new Dosis();
        d.setPlazoSiguiente(30);
        d.setNumOrdenDosis(9);
        d.setTratamientoId(1L);
        created = service.create(d);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        created.setPlazoSiguiente(45);
        service.update(created);
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
        DosisServiceTest test = new DosisServiceTest();
        test.testFindById(1L);
//      test.testFindByTratamientoId();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

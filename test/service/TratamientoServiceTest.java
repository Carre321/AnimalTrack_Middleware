package service;

import com.tonin.animaltrack.model.Tratamiento;
import com.tonin.animaltrack.service.TratamientoService;
import com.tonin.animaltrack.service.impl.TratamientoServiceImpl;

public class TratamientoServiceTest {

    private TratamientoService service = null;
    private Tratamiento created = null;

    public TratamientoServiceTest() {
        this.service = new TratamientoServiceImpl();
    }

    public void testFindById(Long id) {
        Tratamiento dto = service.findById(id);
        System.out.println(dto);
    }

    public void testCreate() {
        Tratamiento e = new Tratamiento();
        e.setNombre("TEST-TRATAMIENTO-" + System.currentTimeMillis());
        created = service.create(e);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        created.setNombre(created.getNombre() + "-UPD");
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
        TratamientoServiceTest test = new TratamientoServiceTest();
        test.testFindById(1L);
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

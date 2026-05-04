package service;

import java.util.List;

import com.tonin.animaltrack.model.VeterinarioGranja;
import com.tonin.animaltrack.service.VeterinarioGranjaService;
import com.tonin.animaltrack.service.impl.VeterinarioGranjaServiceImpl;

public class VeterinarioGranjaServiceTest {

    private VeterinarioGranjaService service = null;
    private Long oldVetId = null;
    private Long oldGranjaId = null;

    public VeterinarioGranjaServiceTest() {
        this.service = new VeterinarioGranjaServiceImpl();
    }

    public void testFindByVeterinarioId() {
        List<VeterinarioGranja> resultados = service.findByVeterinarioId(1L);
        for (VeterinarioGranja e : resultados) {
            System.out.println(e);
        }
    }

    public void testFindByGranjaId() {
        List<VeterinarioGranja> resultados = service.findByGranjaId(1L);
        for (VeterinarioGranja e : resultados) {
            System.out.println(e);
        }
    }

    public void testCreate() {
        VeterinarioGranja e = new VeterinarioGranja();
        e.setVeterinarioId(1L);
        e.setGranjaId(1L);
        VeterinarioGranja created = service.create(e);
        if (created != null) {
            oldVetId = created.getVeterinarioId();
            oldGranjaId = created.getGranjaId();
        }
        System.out.println("Created " + oldVetId + "-" + oldGranjaId);
    }

    public void testUpdate() {
        if (oldVetId == null || oldGranjaId == null) {
            System.out.println("No hay clave creada");
            return;
        }
        VeterinarioGranja e = new VeterinarioGranja();
        e.setVeterinarioId(2L);
        e.setGranjaId(oldGranjaId);
        service.update(oldVetId, oldGranjaId, e);
        oldVetId = 2L;
        System.out.println("Updated " + oldVetId + "-" + oldGranjaId);
    }

    public void deleteTest() {
        if (oldVetId == null || oldGranjaId == null) {
            System.out.println("No hay clave creada");
            return;
        }
        service.delete(oldVetId, oldGranjaId);
        System.out.println("Deleted " + oldVetId + "-" + oldGranjaId);
        oldVetId = null;
        oldGranjaId = null;
    }

    public static void main(String[] args) {
        VeterinarioGranjaServiceTest test = new VeterinarioGranjaServiceTest();
        test.testFindByVeterinarioId();
        test.testFindByGranjaId();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

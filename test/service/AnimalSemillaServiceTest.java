package service;

import java.util.List;

import com.tonin.animaltrack.model.AnimalSemilla;
import com.tonin.animaltrack.service.AnimalSemillaService;
import com.tonin.animaltrack.service.impl.AnimalSemillaServiceImpl;

public class AnimalSemillaServiceTest {

    private AnimalSemillaService service = null;
    private Long oldAnimalId = null;
    private Long oldSemillaId = null;

    public AnimalSemillaServiceTest() {
        this.service = new AnimalSemillaServiceImpl();
    }

    public void testFindByAnimalId() {
        List<AnimalSemilla> resultados = service.findByAnimalId(1L);
        for (AnimalSemilla e : resultados) {
            System.out.println(e);
        }
    }

    public void testFindBySemillaId() {
        List<AnimalSemilla> resultados = service.findBySemillaId(1L);
        for (AnimalSemilla e : resultados) {
            System.out.println(e);
        }
    }

    public void testCreate() {
        AnimalSemilla e = new AnimalSemilla();
        e.setAnimalId(1L);
        e.setSemillaId(2L);
        AnimalSemilla created = service.create(e);
        if (created != null) {
            oldAnimalId = created.getAnimalId();
            oldSemillaId = created.getSemillaId();
        }
        System.out.println("Created " + oldAnimalId + "-" + oldSemillaId);
    }

    public void testUpdate() {
        if (oldAnimalId == null || oldSemillaId == null) {
            System.out.println("No hay clave creada");
            return;
        }
        AnimalSemilla e = new AnimalSemilla();
        e.setAnimalId(oldAnimalId);
        e.setSemillaId(3L);
        service.update(oldAnimalId, oldSemillaId, e);
        oldSemillaId = 3L;
        System.out.println("Updated " + oldAnimalId + "-" + oldSemillaId);
    }

    public void deleteTest() {
        if (oldAnimalId == null || oldSemillaId == null) {
            System.out.println("No hay clave creada");
            return;
        }
        service.delete(oldAnimalId, oldSemillaId);
        System.out.println("Deleted " + oldAnimalId + "-" + oldSemillaId);
        oldAnimalId = null;
        oldSemillaId = null;
    }

    public static void main(String[] args) {
        AnimalSemillaServiceTest test = new AnimalSemillaServiceTest();
        test.testFindByAnimalId();
        test.testFindBySemillaId();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

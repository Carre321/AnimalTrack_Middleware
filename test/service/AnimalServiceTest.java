package service;

import java.sql.Date;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.AnimalCriteria;
import com.tonin.animaltrack.model.Animal;
import com.tonin.animaltrack.model.dto.AnimalDTO;
import com.tonin.animaltrack.service.AnimalService;
import com.tonin.animaltrack.service.impl.AnimalServiceImpl;

public class AnimalServiceTest {

    private AnimalService service = null;
    private AnimalDTO created = null;

    public AnimalServiceTest() {
        this.service = new AnimalServiceImpl();
    }

    public void testFindById(Long id) {
        AnimalDTO dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindBy() {
        AnimalCriteria criteria = new AnimalCriteria();
        criteria.setGranjaId(1L);
        criteria.setCrotalLike("ES270");
        List<AnimalDTO> resultados = service.findByCriteria(criteria);
        for (AnimalDTO dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() {
        Animal a = new Animal();
        a.setNombre("SERVICE-ANIMAL");
        a.setCrotal("SV-AN-" + System.currentTimeMillis());
        a.setFechaNacimiento(Date.valueOf("2024-01-15"));
        a.setFechaBaja(null);
        a.setGranjaId(1L);
        a.setSexoId(2L);
        created = service.create(a);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        AnimalDTO dto = service.findById(created.getId());
        if (dto == null) {
            System.out.println("No existe");
            return;
        }
        Animal a = new Animal();
        a.setId(dto.getId());
        a.setNombre("SERVICE-ANIMAL-UPD");
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
        service.update(a);
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
        AnimalServiceTest test = new AnimalServiceTest();
//      test.testFindById(1L);
      test.testFindBy();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

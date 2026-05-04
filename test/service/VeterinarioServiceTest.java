package service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;
import com.tonin.animaltrack.service.VeterinarioService;
import com.tonin.animaltrack.service.impl.VeterinarioServiceImpl;

public class VeterinarioServiceTest {

    private VeterinarioService service = null;
    private VeterinarioDTO created = null;

    public VeterinarioServiceTest() {
        this.service = new VeterinarioServiceImpl();
    }

    public void testFindById(Long id) {
        VeterinarioDTO dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindBy() {
        VeterinarioCriteria criteria = new VeterinarioCriteria();
        criteria.setMunicipioId(1L);
        List<VeterinarioDTO> resultados = service.findByCriteria(criteria);
        for (VeterinarioDTO dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() {
        Veterinario v = new Veterinario();
        v.setCodigo("VET-S-" + System.currentTimeMillis());
        v.setDni("SVT-" + System.currentTimeMillis());
        v.setNombre("SERVICE");
        v.setApellidos("VETERINARIO");
        v.setTelefono("699999999");
        v.setEmail("service.vet@local");
        v.setMunicipioId(1L);
        created = service.create(v);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        VeterinarioDTO dto = service.findById(created.getId());
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
        v.setTelefono("688888888");
        v.setEmail(dto.getEmail());
        v.setMunicipioId(dto.getMunicipioId());
        service.update(v);
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
        VeterinarioServiceTest test = new VeterinarioServiceTest();
        test.testFindById(1L);
//      test.testFindBy();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

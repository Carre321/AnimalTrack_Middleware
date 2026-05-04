package service;

import com.tonin.animaltrack.model.Sexo;
import com.tonin.animaltrack.service.SexoService;
import com.tonin.animaltrack.service.impl.SexoServiceImpl;

public class SexoServiceTest {

    private SexoService service = null;

    public SexoServiceTest() {
        this.service = new SexoServiceImpl();
    }

    public void testFindById(Long id) {
        Sexo dto = service.findById(id);
        System.out.println(dto);
    }

    public static void main(String[] args) {
        SexoServiceTest test = new SexoServiceTest();
        test.testFindById(1L);
    }
}

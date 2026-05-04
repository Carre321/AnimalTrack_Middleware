package service;

import com.tonin.animaltrack.model.Raza;
import com.tonin.animaltrack.service.RazaService;
import com.tonin.animaltrack.service.impl.RazaServiceImpl;

public class RazaServiceTest {

    private RazaService service = null;

    public RazaServiceTest() {
        this.service = new RazaServiceImpl();
    }

    public void testFindById(Long id) {
        Raza dto = service.findById(id);
        System.out.println(dto);
    }

    public static void main(String[] args) {
        RazaServiceTest test = new RazaServiceTest();
        test.testFindById(1L);
    }
}

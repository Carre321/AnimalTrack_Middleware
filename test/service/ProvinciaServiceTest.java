package service;

import com.tonin.animaltrack.model.Provincia;
import com.tonin.animaltrack.service.ProvinciaService;
import com.tonin.animaltrack.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {

    private ProvinciaService service = null;

    public ProvinciaServiceTest() {
        this.service = new ProvinciaServiceImpl();
    }

    public void testFindById(Long id) {
        Provincia dto = service.findById(id);
        System.out.println(dto);
    }

    public static void main(String[] args) {
        ProvinciaServiceTest test = new ProvinciaServiceTest();
        test.testFindById(1L);
    }
}

package service;

import com.tonin.animaltrack.model.TipoNotificacion;
import com.tonin.animaltrack.service.TipoNotificacionService;
import com.tonin.animaltrack.service.impl.TipoNotificacionServiceImpl;

public class TipoNotificacionServiceTest {

    private TipoNotificacionService service = null;

    public TipoNotificacionServiceTest() {
        this.service = new TipoNotificacionServiceImpl();
    }

    public void testFindById(Long id) {
        TipoNotificacion dto = service.findById(id);
        System.out.println(dto);
    }

    public static void main(String[] args) {
        TipoNotificacionServiceTest test = new TipoNotificacionServiceTest();
        test.testFindById(1L);
    }
}

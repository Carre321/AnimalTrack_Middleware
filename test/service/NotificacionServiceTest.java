package service;

import java.time.LocalDateTime;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.NotificacionCriteria;
import com.tonin.animaltrack.model.Notificacion;
import com.tonin.animaltrack.model.dto.NotificacionDTO;
import com.tonin.animaltrack.service.NotificacionService;
import com.tonin.animaltrack.service.impl.NotificacionServiceImpl;

public class NotificacionServiceTest {

    private NotificacionService service = null;
    private NotificacionDTO created = null;

    public NotificacionServiceTest() {
        this.service = new NotificacionServiceImpl();
    }

    public void testFindById(Long id) {
        NotificacionDTO dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindBy() {
        NotificacionCriteria criteria = new NotificacionCriteria();
        criteria.setEventoId(1L);
        List<NotificacionDTO> resultados = service.findByCriteria(criteria);
        for (NotificacionDTO dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() {
        Notificacion n = new Notificacion();
        n.setEventoId(1L);
        n.setTipo("INFO");
        n.setFechaEmision(LocalDateTime.now());
        n.setDescripcion("Notificacion de prueba");
        n.setTipoNotificacionId(1L);
        created = service.create(n);
        System.out.println("Created " + created);
    }

    public void testUpdate() {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        Notificacion n = new Notificacion();
        n.setId(created.getId());
        n.setEventoId(created.getEventoId());
        n.setTipo(created.getTipo());
        n.setFechaEmision(created.getFechaEmision());
        n.setDescripcion("Notificacion editada");
        n.setTipoNotificacionId(created.getTipoNotificacionId());
        service.update(n);
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
        NotificacionServiceTest test = new NotificacionServiceTest();
        test.testFindById(1L);
//      test.testFindBy();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

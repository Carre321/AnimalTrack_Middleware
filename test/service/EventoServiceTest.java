package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;
import com.tonin.animaltrack.service.EventoService;
import com.tonin.animaltrack.service.impl.EventoServiceImpl;

public class EventoServiceTest {

    private EventoService service = null;
    private EventoDTO created = null;

    public EventoServiceTest() {
        this.service = new EventoServiceImpl();
    }

    public void testFindById(Long id) throws Exception {
        EventoDTO dto = service.findById(id);
        System.out.println(dto);
    }

    public void testFindBy() throws Exception {
        EventoCriteria criteria = new EventoCriteria();
        criteria.setAnimalId(1L);
        List<EventoDTO> resultados = service.findByCriteria(criteria);
        for (EventoDTO dto : resultados) {
            System.out.println(dto);
        }
    }

    public void testCreate() throws Exception {
        Evento e = new Evento();
        e.setAnimalId(1L);
        e.setTipoEventoId(1L);
        e.setFechaHora(LocalDateTime.now());
        created = service.create(e);
        System.out.println("Created " + created);
    }

    public void testUpdate() throws Exception {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        EventoDTO dto = service.findById(created.getId());
        if (dto == null) {
            System.out.println("No existe");
            return;
        }
        Evento e = new Evento();
        e.setId(dto.getId());
        e.setAnimalId(dto.getAnimalId());
        e.setTipoEventoId(dto.getTipoEventoId());
        e.setVeterinarioId(dto.getVeterinarioId());
        e.setFechaHora(dto.getFechaHora());
        e.setSemillaId(dto.getSemillaId());
        e.setPrecioEvento(new BigDecimal("88"));
        e.setDosisId(dto.getDosisId());
        e.setTratamientoId(dto.getTratamientoId());
        service.update(e);
        System.out.println(service.findById(created.getId()));
    }

    public void deleteTest() throws Exception {
        if (created == null || created.getId() == null) {
            System.out.println("No hay ID creado");
            return;
        }
        service.delete(created.getId());
        System.out.println("Deleted " + created.getId());
        created = null;
    }

    public static void main(String[] args) throws Exception {
        EventoServiceTest test = new EventoServiceTest();
        test.testFindById(1L);
//      test.testFindBy();
//      test.testCreate();
//      test.testUpdate();
//      test.deleteTest();
    }
}

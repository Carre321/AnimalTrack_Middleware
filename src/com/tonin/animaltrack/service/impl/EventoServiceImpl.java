package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.EventoDAO;
import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;
import com.tonin.animaltrack.service.EventoService;

public class EventoServiceImpl implements EventoService {

    private EventoDAO eventoDAO = null;

    public EventoServiceImpl() {
        this.eventoDAO = new EventoDAO();
    }

    @Override
    public EventoDTO findById(Long id) {
        return eventoDAO.findById(id);
    }

    @Override
    public List<EventoDTO> findByCriteria(EventoCriteria criteria) {
        return eventoDAO.findBy(criteria);
    }

    @Override
    public List<EventoDTO> findByAnimalId(Long animalId) {
        return eventoDAO.findByAnimalId(animalId);
    }

    @Override
    public List<EventoDTO> findAll() {
        return eventoDAO.getAll();
    }

    @Override
    public EventoDTO create(Evento evento) {
        if (evento == null || evento.getAnimalId() == null || evento.getTipoEventoId() == null) {
            return null;
        }
        Long id = eventoDAO.create(evento);
        return id == null ? null : eventoDAO.findById(id);
    }

    @Override
    public void update(Evento evento) {
        if (evento != null && evento.getId() != null) {
            eventoDAO.update(evento);
        }
    }

    @Override
    public void delete(Long id) {
        eventoDAO.delete(id);
    }
}

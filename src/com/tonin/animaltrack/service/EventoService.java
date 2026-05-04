package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;

public interface EventoService {

    public EventoDTO findById(Long id);

    public List<EventoDTO> findByCriteria(EventoCriteria criteria);

    public List<EventoDTO> findByAnimalId(Long animalId);

    public List<EventoDTO> findAll();

    public EventoDTO create(Evento evento);

    public void update(Evento evento);

    public void delete(Long id);
}

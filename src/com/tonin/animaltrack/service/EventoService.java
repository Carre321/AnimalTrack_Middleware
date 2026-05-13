package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.Results;
import com.tonin.animaltrack.dao.criteria.EventoCriteria;
import com.tonin.animaltrack.model.Evento;
import com.tonin.animaltrack.model.dto.EventoDTO;

public interface EventoService {

    public EventoDTO findById(Long id) throws Exception;

    public List<EventoDTO> findByCriteria(EventoCriteria criteria) throws Exception;

    public Results<EventoDTO> findByCriteria(EventoCriteria criteria, int from, int pageSize) throws Exception;

    public List<EventoDTO> findByAnimalId(Long animalId) throws Exception;

    public List<EventoDTO> findAll() throws Exception;

    public EventoDTO create(Evento evento) throws Exception;

    public void update(Evento evento) throws Exception;

    public void delete(Long id) throws Exception;
}

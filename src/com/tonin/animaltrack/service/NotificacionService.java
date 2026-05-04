package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.NotificacionCriteria;
import com.tonin.animaltrack.model.Notificacion;
import com.tonin.animaltrack.model.dto.NotificacionDTO;

public interface NotificacionService {

    public NotificacionDTO findById(Long id);

    public List<NotificacionDTO> findByCriteria(NotificacionCriteria criteria);

    public List<NotificacionDTO> findByEventoId(Long eventoId);

    public List<NotificacionDTO> findAll();

    public NotificacionDTO create(Notificacion notificacion);

    public void update(Notificacion notificacion);

    public void delete(Long id);
}

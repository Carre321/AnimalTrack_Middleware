package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.NotificacionCriteria;
import com.tonin.animaltrack.model.Notificacion;
import com.tonin.animaltrack.model.dto.NotificacionDTO;

public interface NotificacionService {

    public NotificacionDTO findById(Long id) throws Exception;

    public List<NotificacionDTO> findByCriteria(NotificacionCriteria criteria) throws Exception;

    public List<NotificacionDTO> findByEventoId(Long eventoId) throws Exception;

    public List<NotificacionDTO> findAll() throws Exception;

    public NotificacionDTO create(Notificacion notificacion) throws Exception;

    public void update(Notificacion notificacion) throws Exception;

    public void delete(Long id) throws Exception;
}

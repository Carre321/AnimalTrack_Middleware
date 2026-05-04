package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.NotificacionDAO;
import com.tonin.animaltrack.dao.criteria.NotificacionCriteria;
import com.tonin.animaltrack.model.Notificacion;
import com.tonin.animaltrack.model.dto.NotificacionDTO;
import com.tonin.animaltrack.service.NotificacionService;

public class NotificacionServiceImpl implements NotificacionService {

    private NotificacionDAO notificacionDAO = null;

    public NotificacionServiceImpl() {
        this.notificacionDAO = new NotificacionDAO();
    }

    @Override
    public NotificacionDTO findById(Long id) {
        return notificacionDAO.findById(id);
    }

    @Override
    public List<NotificacionDTO> findByCriteria(NotificacionCriteria criteria) {
        return notificacionDAO.findBy(criteria);
    }

    @Override
    public List<NotificacionDTO> findByEventoId(Long eventoId) {
        return notificacionDAO.findByEventoId(eventoId);
    }

    @Override
    public List<NotificacionDTO> findAll() {
        return notificacionDAO.getAll();
    }

    @Override
    public NotificacionDTO create(Notificacion notificacion) {
        if (notificacion == null || notificacion.getEventoId() == null || notificacion.getTipoNotificacionId() == null) {
            return null;
        }
        Long id = notificacionDAO.create(notificacion);
        return id == null ? null : notificacionDAO.findById(id);
    }

    @Override
    public void update(Notificacion notificacion) {
        if (notificacion != null && notificacion.getId() != null) {
            notificacionDAO.update(notificacion);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            notificacionDAO.delete(id);
        }
    }
}

package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.TipoNotificacionDAO;
import com.tonin.animaltrack.model.TipoNotificacion;
import com.tonin.animaltrack.service.TipoNotificacionService;

public class TipoNotificacionServiceImpl implements TipoNotificacionService {

    private TipoNotificacionDAO tipoNotificacionDAO = null;

    public TipoNotificacionServiceImpl() {
        this.tipoNotificacionDAO = new TipoNotificacionDAO();
    }

    @Override
    public TipoNotificacion findById(Long id) {
        return tipoNotificacionDAO.findById(id);
    }

    @Override
    public List<TipoNotificacion> findAll() {
        return tipoNotificacionDAO.getAll();
    }
}

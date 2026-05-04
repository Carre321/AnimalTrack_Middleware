package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.TipoNotificacion;

public interface TipoNotificacionService {

    public TipoNotificacion findById(Long id);

    public List<TipoNotificacion> findAll();
}

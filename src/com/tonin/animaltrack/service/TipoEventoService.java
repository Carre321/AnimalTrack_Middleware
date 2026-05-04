package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.TipoEvento;

public interface TipoEventoService {

    public TipoEvento findById(Long id);

    public List<TipoEvento> findAll();
}

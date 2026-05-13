package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.TipoEvento;

public interface TipoEventoService {

    public TipoEvento findById(Long id) throws Exception;

    public List<TipoEvento> findAll() throws Exception;
}

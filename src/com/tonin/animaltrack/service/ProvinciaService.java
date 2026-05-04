package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Provincia;

public interface ProvinciaService {

    public Provincia findById(Long id);

    public List<Provincia> findAll();
}

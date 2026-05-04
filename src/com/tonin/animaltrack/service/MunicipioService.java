package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Municipio;

public interface MunicipioService {

    public Municipio findById(Long id);

    public List<Municipio> findByProvinciaId(Long provinciaId);

    public List<Municipio> findAll();
}

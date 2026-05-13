package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Municipio;

public interface MunicipioService {

    public Municipio findById(Long id) throws Exception;

    public List<Municipio> findByProvinciaId(Long provinciaId) throws Exception;

    public List<Municipio> findAll() throws Exception;
}

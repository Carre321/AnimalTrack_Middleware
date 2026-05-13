package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Raza;

public interface RazaService {

    public Raza findById(Long id) throws Exception;

    public List<Raza> findAll() throws Exception;
}

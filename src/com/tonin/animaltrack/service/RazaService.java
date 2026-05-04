package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Raza;

public interface RazaService {

    public Raza findById(Long id);

    public List<Raza> findAll();
}

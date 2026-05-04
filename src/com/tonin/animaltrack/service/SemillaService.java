package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Semilla;

public interface SemillaService {

    public Semilla findById(Long id);

    public Semilla findByCodigo(String codigo);

    public List<Semilla> findAll();

    public Semilla create(Semilla semilla);

    public void update(Semilla semilla);

    public void delete(Long id);
}

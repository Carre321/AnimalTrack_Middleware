package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Semilla;

public interface SemillaService {

    public Semilla findById(Long id) throws Exception;

    public Semilla findByCodigo(String codigo) throws Exception;

    public List<Semilla> findAll() throws Exception;

    public Semilla create(Semilla semilla) throws Exception;

    public void update(Semilla semilla) throws Exception;

    public void delete(Long id) throws Exception;
}

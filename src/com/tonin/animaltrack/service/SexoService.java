package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Sexo;

public interface SexoService {

    public Sexo findById(Long id) throws Exception;

    public List<Sexo> findAll() throws Exception;
}

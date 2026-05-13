package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Dosis;

public interface DosisService {

    public Dosis findById(Long id) throws Exception;

    public List<Dosis> findByTratamientoId(Long tratamientoId) throws Exception;

    public List<Dosis> findAll() throws Exception;

    public Dosis create(Dosis dosis) throws Exception;

    public void update(Dosis dosis) throws Exception;

    public void delete(Long id) throws Exception;
}

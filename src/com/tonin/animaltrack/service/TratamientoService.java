package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Tratamiento;

public interface TratamientoService {

    public Tratamiento findById(Long id) throws Exception;

    public List<Tratamiento> findAll() throws Exception;

    public Tratamiento create(Tratamiento tratamiento) throws Exception;

    public void update(Tratamiento tratamiento) throws Exception;

    public void delete(Long id) throws Exception;
}

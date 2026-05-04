package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Tratamiento;

public interface TratamientoService {

    public Tratamiento findById(Long id);

    public List<Tratamiento> findAll();

    public Tratamiento create(Tratamiento tratamiento);

    public void update(Tratamiento tratamiento);

    public void delete(Long id);
}

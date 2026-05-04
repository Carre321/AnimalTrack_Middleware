package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.Dosis;

public interface DosisService {

    public Dosis findById(Long id);

    public List<Dosis> findByTratamientoId(Long tratamientoId);

    public List<Dosis> findAll();

    public Dosis create(Dosis dosis);

    public void update(Dosis dosis);

    public void delete(Long id);
}

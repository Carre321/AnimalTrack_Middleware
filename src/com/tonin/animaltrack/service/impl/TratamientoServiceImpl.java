package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.TratamientoDAO;
import com.tonin.animaltrack.model.Tratamiento;
import com.tonin.animaltrack.service.TratamientoService;

public class TratamientoServiceImpl implements TratamientoService {

    private TratamientoDAO tratamientoDAO = null;

    public TratamientoServiceImpl() {
        this.tratamientoDAO = new TratamientoDAO();
    }

    @Override
    public Tratamiento findById(Long id) {
        return tratamientoDAO.findById(id);
    }

    @Override
    public List<Tratamiento> findAll() {
        return tratamientoDAO.getAll();
    }

    @Override
    public Tratamiento create(Tratamiento tratamiento) {
        if (tratamiento == null || tratamiento.getNombre() == null) {
            return null;
        }
        Long id = tratamientoDAO.create(tratamiento);
        return id == null ? null : tratamientoDAO.findById(id);
    }

    @Override
    public void update(Tratamiento tratamiento) {
        if (tratamiento != null && tratamiento.getId() != null) {
            tratamientoDAO.update(tratamiento);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            tratamientoDAO.delete(id);
        }
    }
}

package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.DosisDAO;
import com.tonin.animaltrack.model.Dosis;
import com.tonin.animaltrack.service.DosisService;

public class DosisServiceImpl implements DosisService {

    private DosisDAO dosisDAO = null;

    public DosisServiceImpl() {
        this.dosisDAO = new DosisDAO();
    }

    @Override
    public Dosis findById(Long id) {
        return dosisDAO.findById(id);
    }

    @Override
    public List<Dosis> findByTratamientoId(Long tratamientoId) {
        return dosisDAO.findByTratamientoId(tratamientoId);
    }

    @Override
    public List<Dosis> findAll() {
        return dosisDAO.getAll();
    }

    @Override
    public Dosis create(Dosis dosis) {
        if (dosis == null || dosis.getTratamientoId() == null) {
            return null;
        }
        Long id = dosisDAO.create(dosis);
        return id == null ? null : dosisDAO.findById(id);
    }

    @Override
    public void update(Dosis dosis) {
        if (dosis != null && dosis.getId() != null) {
            dosisDAO.update(dosis);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            dosisDAO.delete(id);
        }
    }
}

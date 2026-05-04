package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.SemillaDAO;
import com.tonin.animaltrack.model.Semilla;
import com.tonin.animaltrack.service.SemillaService;

public class SemillaServiceImpl implements SemillaService {

    private SemillaDAO semillaDAO = null;

    public SemillaServiceImpl() {
        this.semillaDAO = new SemillaDAO();
    }

    @Override
    public Semilla findById(Long id) {
        return semillaDAO.findById(id);
    }

    @Override
    public Semilla findByCodigo(String codigo) {
        return semillaDAO.findByCodigo(codigo);
    }

    @Override
    public List<Semilla> findAll() {
        return semillaDAO.getAll();
    }

    @Override
    public Semilla create(Semilla semilla) {
        if (semilla == null || semilla.getCodigo() == null) {
            return null;
        }
        Long id = semillaDAO.create(semilla);
        return id == null ? null : semillaDAO.findById(id);
    }

    @Override
    public void update(Semilla semilla) {
        if (semilla != null && semilla.getId() != null) {
            semillaDAO.update(semilla);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            semillaDAO.delete(id);
        }
    }
}

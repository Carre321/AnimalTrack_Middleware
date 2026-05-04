package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.ProvinciaDAO;
import com.tonin.animaltrack.model.Provincia;
import com.tonin.animaltrack.service.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService {

    private ProvinciaDAO provinciaDAO = null;

    public ProvinciaServiceImpl() {
        this.provinciaDAO = new ProvinciaDAO();
    }

    @Override
    public Provincia findById(Long id) {
        return provinciaDAO.findById(id);
    }

    @Override
    public List<Provincia> findAll() {
        return provinciaDAO.getAll();
    }
}

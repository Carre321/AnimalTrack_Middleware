package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.RazaDAO;
import com.tonin.animaltrack.model.Raza;
import com.tonin.animaltrack.service.RazaService;

public class RazaServiceImpl implements RazaService {

    private RazaDAO razaDAO = null;

    public RazaServiceImpl() {
        this.razaDAO = new RazaDAO();
    }

    @Override
    public Raza findById(Long id) {
        return razaDAO.findById(id);
    }

    @Override
    public List<Raza> findAll() {
        return razaDAO.getAll();
    }
}

package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.SexoDAO;
import com.tonin.animaltrack.model.Sexo;
import com.tonin.animaltrack.service.SexoService;

public class SexoServiceImpl implements SexoService {

    private SexoDAO sexoDAO = null;

    public SexoServiceImpl() {
        this.sexoDAO = new SexoDAO();
    }

    @Override
    public Sexo findById(Long id) {
        return sexoDAO.findById(id);
    }

    @Override
    public List<Sexo> findAll() {
        return sexoDAO.getAll();
    }
}

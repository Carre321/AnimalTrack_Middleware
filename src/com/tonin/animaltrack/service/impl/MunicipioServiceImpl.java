package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.MunicipioDAO;
import com.tonin.animaltrack.model.Municipio;
import com.tonin.animaltrack.service.MunicipioService;

public class MunicipioServiceImpl implements MunicipioService {

    private MunicipioDAO municipioDAO = null;

    public MunicipioServiceImpl() {
        this.municipioDAO = new MunicipioDAO();
    }

    @Override
    public Municipio findById(Long id) {
        return municipioDAO.findById(id);
    }

    @Override
    public List<Municipio> findByProvinciaId(Long provinciaId) {
        return municipioDAO.findByProvinciaId(provinciaId);
    }

    @Override
    public List<Municipio> findAll() {
        return municipioDAO.getAll();
    }
}

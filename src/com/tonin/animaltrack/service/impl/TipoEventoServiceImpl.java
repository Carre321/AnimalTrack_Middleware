package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.TipoEventoDAO;
import com.tonin.animaltrack.model.TipoEvento;
import com.tonin.animaltrack.service.TipoEventoService;

public class TipoEventoServiceImpl implements TipoEventoService {

    private TipoEventoDAO tipoEventoDAO = null;

    public TipoEventoServiceImpl() {
        this.tipoEventoDAO = new TipoEventoDAO();
    }

    @Override
    public TipoEvento findById(Long id) {
        return tipoEventoDAO.findById(id);
    }

    @Override
    public List<TipoEvento> findAll() {
        return tipoEventoDAO.getAll();
    }
}

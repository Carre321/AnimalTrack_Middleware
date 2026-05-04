package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.GanaderoDAO;
import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;
import com.tonin.animaltrack.service.GanaderoService;

public class GanaderoServiceImpl implements GanaderoService {

    private GanaderoDAO ganaderoDAO = null;

    public GanaderoServiceImpl() {
        this.ganaderoDAO = new GanaderoDAO();
    }

    @Override
    public GanaderoDTO findById(Long id) {
        return ganaderoDAO.findById(id);
    }

    @Override
    public List<GanaderoDTO> findByCriteria(GanaderoCriteria criteria) {
        return ganaderoDAO.findBy(criteria);
    }

    @Override
    public List<GanaderoDTO> findByMunicipioId(Long municipioId) {
        return ganaderoDAO.findByMunicipioId(municipioId);
    }

    @Override
    public List<GanaderoDTO> findAll() {
        return ganaderoDAO.getAll();
    }

    @Override
    public GanaderoDTO create(Ganadero ganadero) {
        if (ganadero == null || ganadero.getNombre() == null || ganadero.getMunicipioId() == null) {
            return null;
        }
        Long id = ganaderoDAO.create(ganadero);
        return id == null ? null : ganaderoDAO.findById(id);
    }

    @Override
    public void update(Ganadero ganadero) {
        if (ganadero != null && ganadero.getId() != null) {
            ganaderoDAO.update(ganadero);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            ganaderoDAO.delete(id);
        }
    }
}

package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.GranjaDAO;
import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;
import com.tonin.animaltrack.service.GranjaService;

public class GranjaServiceImpl implements GranjaService {

    private static final String REQUIRED_DATA_MESSAGE = "Faltan datos obligatorios. Revisa los datos introducidos.";

    private GranjaDAO granjaDAO = null;

    public GranjaServiceImpl() {
        this.granjaDAO = new GranjaDAO();
    }

    @Override
    public GranjaDTO findById(Long id) {
        return granjaDAO.findById(id);
    }

    @Override
    public List<GranjaDTO> findByCriteria(GranjaCriteria criteria) {
        return granjaDAO.findBy(criteria);
    }

    @Override
    public List<GranjaDTO> findByGanaderoId(Long ganaderoId) {
        return granjaDAO.findByGanaderoId(ganaderoId);
    }

    @Override
    public List<GranjaDTO> findAll() {
        return granjaDAO.getAll();
    }

    @Override
    public GranjaDTO create(Granja granja) {
        validateForSave(granja);
        Long id = granjaDAO.create(granja);
        return id == null ? null : granjaDAO.findById(id);
    }

    @Override
    public void update(Granja granja) {
        if (granja != null && granja.getId() != null) {
            validateForSave(granja);
            granjaDAO.update(granja);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            granjaDAO.delete(id);
        }
    }

    private void validateForSave(Granja granja) {
        if (granja == null || isBlank(granja.getNombre()) || isBlank(granja.getDireccion())
                || granja.getMunicipioId() == null || granja.getGanaderoId() == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        granja.setNombre(granja.getNombre().trim());
        granja.setDireccion(granja.getDireccion().trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

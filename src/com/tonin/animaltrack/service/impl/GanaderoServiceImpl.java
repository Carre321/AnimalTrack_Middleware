package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.GanaderoDAO;
import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;
import com.tonin.animaltrack.service.GanaderoService;

public class GanaderoServiceImpl implements GanaderoService {

    private static final String REQUIRED_DATA_MESSAGE = "Faltan datos obligatorios. Revisa los datos introducidos.";

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
        validateForSave(ganadero);
        Long id = ganaderoDAO.create(ganadero);
        return id == null ? null : ganaderoDAO.findById(id);
    }

    @Override
    public void update(Ganadero ganadero) {
        if (ganadero != null && ganadero.getId() != null) {
            validateForSave(ganadero);
            ganaderoDAO.update(ganadero);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            ganaderoDAO.delete(id);
        }
    }

    private void validateForSave(Ganadero ganadero) {
        if (ganadero == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (isBlank(ganadero.getNombre())) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (ganadero.getMunicipioId() == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }

        String dni = normalizeDni(ganadero.getDni());
        if (dni == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        ganadero.setDni(dni);

        GanaderoCriteria criteria = new GanaderoCriteria();
        criteria.setDni(dni);
        List<GanaderoDTO> matches = ganaderoDAO.findBy(criteria);
        if (matches == null) {
            throw new IllegalStateException("No se pudo comprobar si el DNI ya existe.");
        }
        for (GanaderoDTO existing : matches) {
            if (existing.getId() != null && !existing.getId().equals(ganadero.getId())) {
                throw new IllegalArgumentException("No se pudo guardar el ganadero. Revisa los datos introducidos.");
            }
        }
    }

    private String normalizeDni(String dni) {
        if (isBlank(dni)) {
            return null;
        }
        return dni.trim().toUpperCase();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

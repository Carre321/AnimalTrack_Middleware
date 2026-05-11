package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.VeterinarioDAO;
import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;
import com.tonin.animaltrack.service.VeterinarioService;

public class VeterinarioServiceImpl implements VeterinarioService {

    private static final String REQUIRED_DATA_MESSAGE = "Faltan datos obligatorios. Revisa los datos introducidos.";

    private VeterinarioDAO veterinarioDAO = null;

    public VeterinarioServiceImpl() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    @Override
    public VeterinarioDTO findById(Long id) {
        return veterinarioDAO.findById(id);
    }

    @Override
    public List<VeterinarioDTO> findByCriteria(VeterinarioCriteria criteria) {
        return veterinarioDAO.findBy(criteria);
    }

    @Override
    public List<VeterinarioDTO> findByMunicipioId(Long municipioId) {
        return veterinarioDAO.findByMunicipioId(municipioId);
    }

    @Override
    public List<VeterinarioDTO> findAll() {
        return veterinarioDAO.getAll();
    }

    @Override
    public VeterinarioDTO create(Veterinario veterinario) {
        validateForSave(veterinario);
        Long id = veterinarioDAO.create(veterinario);
        return id == null ? null : veterinarioDAO.findById(id);
    }

    @Override
    public void update(Veterinario veterinario) {
        if (veterinario != null && veterinario.getId() != null) {
            validateForSave(veterinario);
            veterinarioDAO.update(veterinario);
        }
    }

    @Override
    public void delete(Long id) {
        veterinarioDAO.delete(id);
    }

    private void validateForSave(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (isBlank(veterinario.getCodigo())) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (isBlank(veterinario.getNombre())) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }
        if (veterinario.getMunicipioId() == null) {
            throw new IllegalArgumentException(REQUIRED_DATA_MESSAGE);
        }

        String codigo = normalize(veterinario.getCodigo());
        veterinario.setCodigo(codigo);
        assertUniqueCodigo(veterinario, codigo);

        String dni = normalize(veterinario.getDni());
        veterinario.setDni(dni);
        if (dni != null) {
            assertUniqueDni(veterinario, dni);
        }
    }

    private void assertUniqueCodigo(Veterinario veterinario, String codigo) {
        VeterinarioCriteria criteria = new VeterinarioCriteria();
        criteria.setCodigo(codigo);
        assertNoOtherVeterinario(veterinario, veterinarioDAO.findBy(criteria));
    }

    private void assertUniqueDni(Veterinario veterinario, String dni) {
        VeterinarioCriteria criteria = new VeterinarioCriteria();
        criteria.setDni(dni);
        assertNoOtherVeterinario(veterinario, veterinarioDAO.findBy(criteria));
    }

    private void assertNoOtherVeterinario(Veterinario veterinario, List<VeterinarioDTO> matches) {
        if (matches == null) {
            throw new IllegalStateException("No se pudo comprobar si el veterinario ya existe.");
        }
        for (VeterinarioDTO existing : matches) {
            if (existing.getId() != null && !existing.getId().equals(veterinario.getId())) {
                throw new IllegalArgumentException("No se pudo guardar el veterinario. Revisa los datos introducidos.");
            }
        }
    }

    private String normalize(String value) {
        if (isBlank(value)) {
            return null;
        }
        return value.trim().toUpperCase();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

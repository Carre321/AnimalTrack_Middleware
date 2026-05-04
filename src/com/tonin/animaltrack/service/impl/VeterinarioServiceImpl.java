package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.VeterinarioDAO;
import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;
import com.tonin.animaltrack.service.VeterinarioService;

public class VeterinarioServiceImpl implements VeterinarioService {

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
        if (veterinario == null || veterinario.getCodigo() == null || veterinario.getNombre() == null || veterinario.getMunicipioId() == null) {
            return null;
        }
        Long id = veterinarioDAO.create(veterinario);
        return id == null ? null : veterinarioDAO.findById(id);
    }

    @Override
    public void update(Veterinario veterinario) {
        if (veterinario != null && veterinario.getId() != null) {
            veterinarioDAO.update(veterinario);
        }
    }

    @Override
    public void delete(Long id) {
        veterinarioDAO.delete(id);
    }
}

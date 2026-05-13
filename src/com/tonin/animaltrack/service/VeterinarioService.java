package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;

public interface VeterinarioService {

    public VeterinarioDTO findById(Long id) throws Exception;

    public List<VeterinarioDTO> findByCriteria(VeterinarioCriteria criteria) throws Exception;

    public List<VeterinarioDTO> findByMunicipioId(Long municipioId) throws Exception;

    public List<VeterinarioDTO> findAll() throws Exception;

    public VeterinarioDTO create(Veterinario veterinario) throws Exception;

    public void update(Veterinario veterinario) throws Exception;

    public void delete(Long id) throws Exception;
}

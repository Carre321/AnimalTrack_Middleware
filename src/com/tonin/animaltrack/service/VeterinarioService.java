package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.VeterinarioCriteria;
import com.tonin.animaltrack.model.Veterinario;
import com.tonin.animaltrack.model.dto.VeterinarioDTO;

public interface VeterinarioService {

    public VeterinarioDTO findById(Long id);

    public List<VeterinarioDTO> findByCriteria(VeterinarioCriteria criteria);

    public List<VeterinarioDTO> findByMunicipioId(Long municipioId);

    public List<VeterinarioDTO> findAll();

    public VeterinarioDTO create(Veterinario veterinario);

    public void update(Veterinario veterinario);

    public void delete(Long id);
}

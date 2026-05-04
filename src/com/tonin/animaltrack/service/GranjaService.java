package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;

public interface GranjaService {

    public GranjaDTO findById(Long id);

    public List<GranjaDTO> findByCriteria(GranjaCriteria criteria);

    public List<GranjaDTO> findByGanaderoId(Long ganaderoId);

    public List<GranjaDTO> findAll();

    public GranjaDTO create(Granja granja);

    public void update(Granja granja);

    public void delete(Long id);
}

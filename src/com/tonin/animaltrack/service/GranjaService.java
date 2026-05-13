package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.GranjaCriteria;
import com.tonin.animaltrack.model.Granja;
import com.tonin.animaltrack.model.dto.GranjaDTO;

public interface GranjaService {

    public GranjaDTO findById(Long id) throws Exception;

    public List<GranjaDTO> findByCriteria(GranjaCriteria criteria) throws Exception;

    public List<GranjaDTO> findByGanaderoId(Long ganaderoId) throws Exception;

    public List<GranjaDTO> findAll() throws Exception;

    public GranjaDTO create(Granja granja) throws Exception;

    public void update(Granja granja) throws Exception;

    public void delete(Long id) throws Exception;
}

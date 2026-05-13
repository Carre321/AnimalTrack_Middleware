package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;

public interface GanaderoService {

    public GanaderoDTO findById(Long id) throws Exception;

    public List<GanaderoDTO> findByCriteria(GanaderoCriteria criteria) throws Exception;

    public List<GanaderoDTO> findByMunicipioId(Long municipioId) throws Exception;

    public List<GanaderoDTO> findAll() throws Exception;

    public GanaderoDTO create(Ganadero ganadero) throws Exception;

    public void update(Ganadero ganadero) throws Exception;

    public void delete(Long id) throws Exception;
}

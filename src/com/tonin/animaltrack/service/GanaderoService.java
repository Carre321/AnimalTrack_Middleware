package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.criteria.GanaderoCriteria;
import com.tonin.animaltrack.model.Ganadero;
import com.tonin.animaltrack.model.dto.GanaderoDTO;

public interface GanaderoService {

    public GanaderoDTO findById(Long id);

    public List<GanaderoDTO> findByCriteria(GanaderoCriteria criteria);

    public List<GanaderoDTO> findByMunicipioId(Long municipioId);

    public List<GanaderoDTO> findAll();

    public GanaderoDTO create(Ganadero ganadero);

    public void update(Ganadero ganadero);

    public void delete(Long id);
}

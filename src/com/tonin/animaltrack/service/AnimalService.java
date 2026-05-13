package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.Results;
import com.tonin.animaltrack.dao.criteria.AnimalCriteria;
import com.tonin.animaltrack.model.Animal;
import com.tonin.animaltrack.model.dto.AnimalDTO;

public interface AnimalService {

    public AnimalDTO findById(Long id) throws Exception;

    public AnimalDTO findByCrotal(String crotal) throws Exception;

    public List<AnimalDTO> findByCriteria(AnimalCriteria criteria) throws Exception;
    
    public List<AnimalDTO> findAll() throws Exception;

    public AnimalDTO create(Animal animal) throws Exception;

    public boolean update(Animal animal) throws Exception;

    public boolean delete(Long id) throws Exception;

	Results<AnimalDTO> findByCriteria(AnimalCriteria criteria, int from, int pageSize) throws Exception;

}

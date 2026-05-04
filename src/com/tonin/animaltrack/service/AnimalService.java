package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.dao.Results;
import com.tonin.animaltrack.dao.criteria.AnimalCriteria;
import com.tonin.animaltrack.model.Animal;
import com.tonin.animaltrack.model.dto.AnimalDTO;

public interface AnimalService {

    public AnimalDTO findById(Long id);

    public AnimalDTO findByCrotal(String crotal);

    public List<AnimalDTO> findByCriteria(AnimalCriteria criteria);
    
    public List<AnimalDTO> findAll();

    public AnimalDTO create(Animal animal);

    public void update(Animal animal);

    public void delete(Long id);

	Results<AnimalDTO> findByCriteria(AnimalCriteria criteria, int from, int pageSize);

}

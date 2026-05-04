package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.AnimalDAO;
import com.tonin.animaltrack.dao.Results;
import com.tonin.animaltrack.dao.criteria.AnimalCriteria;
import com.tonin.animaltrack.model.Animal;
import com.tonin.animaltrack.model.dto.AnimalDTO;
import com.tonin.animaltrack.service.AnimalService;

public class AnimalServiceImpl implements AnimalService {

    private AnimalDAO animalDAO = null;

    public AnimalServiceImpl() {
        this.animalDAO = new AnimalDAO();
    }

    @Override
    public AnimalDTO findById(Long id) {
        return animalDAO.findById(id);
    }

    @Override
    public AnimalDTO findByCrotal(String crotal) {
        return animalDAO.findByCrotal(crotal);
    }

    @Override
    public Results<AnimalDTO> findByCriteria(AnimalCriteria criteria, int from, int pageSize) {
        return animalDAO.findBy(criteria, from, pageSize);
    }

    @Override
    public List<AnimalDTO> findAll() {
        Results<AnimalDTO> results = animalDAO.findBy(new AnimalCriteria(), 1, 10);
        return results == null ? null : results.getPageResults();
    }

    @Override
    public AnimalDTO create(Animal animal) {
        if (animal == null || animal.getCrotal() == null || animal.getSexoId() == null || animal.getGranjaId() == null) {
            return null;
        }
        Long id = animalDAO.create(animal);
        return id == null ? null : animalDAO.findById(id);
    }

    @Override
    public void update(Animal animal) {
        if (animal != null && animal.getId() != null) {
            animalDAO.update(animal);
        }
    }

    @Override
    public void delete(Long id) {
        animalDAO.delete(id);
    }

	@Override
	public List<AnimalDTO> findByCriteria(AnimalCriteria criteria) {
		Results<AnimalDTO> results = animalDAO.findBy(criteria, 1, Integer.MAX_VALUE);
		return results == null ? null : results.getPageResults();
	}
}

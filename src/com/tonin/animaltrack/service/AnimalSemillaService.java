package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.AnimalSemilla;

public interface AnimalSemillaService {

    public List<AnimalSemilla> findByAnimalId(Long animalId) throws Exception;

    public List<AnimalSemilla> findBySemillaId(Long semillaId) throws Exception;

    public AnimalSemilla create(AnimalSemilla animalSemilla) throws Exception;

    public void update(Long oldAnimalId, Long oldSemillaId, AnimalSemilla animalSemilla) throws Exception;

    public void delete(Long animalId, Long semillaId) throws Exception;
}

package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.AnimalSemilla;

public interface AnimalSemillaService {

    public List<AnimalSemilla> findByAnimalId(Long animalId);

    public List<AnimalSemilla> findBySemillaId(Long semillaId);

    public AnimalSemilla create(AnimalSemilla animalSemilla);

    public void update(Long oldAnimalId, Long oldSemillaId, AnimalSemilla animalSemilla);

    public void delete(Long animalId, Long semillaId);
}

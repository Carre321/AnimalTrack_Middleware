package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.AnimalSemillaDAO;
import com.tonin.animaltrack.model.AnimalSemilla;
import com.tonin.animaltrack.service.AnimalSemillaService;

public class AnimalSemillaServiceImpl implements AnimalSemillaService {

    private AnimalSemillaDAO animalSemillaDAO = null;

    public AnimalSemillaServiceImpl() {
        this.animalSemillaDAO = new AnimalSemillaDAO();
    }

    @Override
    public List<AnimalSemilla> findByAnimalId(Long animalId) {
        return animalSemillaDAO.findByAnimalId(animalId);
    }

    @Override
    public List<AnimalSemilla> findBySemillaId(Long semillaId) {
        return animalSemillaDAO.findBySemillaId(semillaId);
    }

    @Override
    public AnimalSemilla create(AnimalSemilla animalSemilla) {
        if (animalSemilla == null || animalSemilla.getAnimalId() == null || animalSemilla.getSemillaId() == null) {
            return null;
        }
        animalSemillaDAO.create(animalSemilla);
        return animalSemilla;
    }

    @Override
    public void update(Long oldAnimalId, Long oldSemillaId, AnimalSemilla animalSemilla) {
        if (oldAnimalId != null && oldSemillaId != null && animalSemilla != null
                && animalSemilla.getAnimalId() != null && animalSemilla.getSemillaId() != null) {
            animalSemillaDAO.update(oldAnimalId, oldSemillaId, animalSemilla);
        }
    }

    @Override
    public void delete(Long animalId, Long semillaId) {
        if (animalId != null && semillaId != null) {
            animalSemillaDAO.delete(animalId, semillaId);
        }
    }
}

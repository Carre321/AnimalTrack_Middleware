package com.tonin.animaltrack.dao.criteria;

public class AnimalSemillaCriteria {

    private Long animalId;
    private Long semillaId;

    public AnimalSemillaCriteria() {
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getSemillaId() {
        return semillaId;
    }

    public void setSemillaId(Long semillaId) {
        this.semillaId = semillaId;
    }
}
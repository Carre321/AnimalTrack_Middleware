package com.tonin.animaltrack.model;

public class AnimalSemilla extends AbstractValueObject {

    private Long animalId;
    private Long semillaId;

    public AnimalSemilla() {
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


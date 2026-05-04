package com.tonin.animaltrack.model;

public class VeterinarioGranja extends AbstractValueObject {

    private Long veterinarioId;
    private Long granjaId;

    public VeterinarioGranja() {
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Long getGranjaId() {
        return granjaId;
    }

    public void setGranjaId(Long granjaId) {
        this.granjaId = granjaId;
    }
}


package com.tonin.animaltrack.dao.criteria;

public class VeterinarioGranjaCriteria {

    private Long veterinarioId;
    private Long granjaId;

    public VeterinarioGranjaCriteria() {
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
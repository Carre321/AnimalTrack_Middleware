package com.tonin.animaltrack.dao.criteria;

public class DosisCriteria {

    private Long id;
    private Integer plazoSiguienteDesde;
    private Integer plazoSiguienteHasta;
    private Integer numOrdenDosis;
    private Long tratamientoId;

    public DosisCriteria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlazoSiguienteDesde() {
        return plazoSiguienteDesde;
    }

    public void setPlazoSiguienteDesde(Integer plazoSiguienteDesde) {
        this.plazoSiguienteDesde = plazoSiguienteDesde;
    }

    public Integer getPlazoSiguienteHasta() {
        return plazoSiguienteHasta;
    }

    public void setPlazoSiguienteHasta(Integer plazoSiguienteHasta) {
        this.plazoSiguienteHasta = plazoSiguienteHasta;
    }

    public Integer getNumOrdenDosis() {
        return numOrdenDosis;
    }

    public void setNumOrdenDosis(Integer numOrdenDosis) {
        this.numOrdenDosis = numOrdenDosis;
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
    }
}
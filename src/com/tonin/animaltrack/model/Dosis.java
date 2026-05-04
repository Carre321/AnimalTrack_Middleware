package com.tonin.animaltrack.model;

public class Dosis extends AbstractValueObject {

    private Long id;
    private Integer plazoSiguiente;
    private Integer numOrdenDosis;
    private Long tratamientoId;

    public Dosis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlazoSiguiente() {
        return plazoSiguiente;
    }

    public void setPlazoSiguiente(Integer plazoSiguiente) {
        this.plazoSiguiente = plazoSiguiente;
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


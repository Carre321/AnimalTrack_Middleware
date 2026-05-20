package com.tonin.animaltrack.dao.criteria;

public class SemillaCriteria {

    private String codigoLike;
    private String nombreLike;
    private Long razaId;

    public SemillaCriteria() {
    }

    public String getCodigoLike() {
        return codigoLike;
    }

    public void setCodigoLike(String codigoLike) {
        this.codigoLike = codigoLike;
    }

    public String getNombreLike() {
        return nombreLike;
    }

    public void setNombreLike(String nombreLike) {
        this.nombreLike = nombreLike;
    }

    public Long getRazaId() {
        return razaId;
    }

    public void setRazaId(Long razaId) {
        this.razaId = razaId;
    }
}

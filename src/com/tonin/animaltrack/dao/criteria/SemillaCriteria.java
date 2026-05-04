package com.tonin.animaltrack.dao.criteria;

public class SemillaCriteria {

    private Long id;
    private String codigo;
    private String codigoLike;
    private String descripcionLike;

    public SemillaCriteria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoLike() {
        return codigoLike;
    }

    public void setCodigoLike(String codigoLike) {
        this.codigoLike = codigoLike;
    }

    public String getDescripcionLike() {
        return descripcionLike;
    }

    public void setDescripcionLike(String descripcionLike) {
        this.descripcionLike = descripcionLike;
    }
}
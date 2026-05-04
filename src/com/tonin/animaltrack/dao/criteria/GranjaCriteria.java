package com.tonin.animaltrack.dao.criteria;

public class GranjaCriteria {

    private Long id;
    private String nombre;
    private String nombreLike;
    private String direccionLike;
    private Long municipioId;
    private Long ganaderoId;

    public GranjaCriteria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreLike() {
        return nombreLike;
    }

    public void setNombreLike(String nombreLike) {
        this.nombreLike = nombreLike;
    }

    public String getDireccionLike() {
        return direccionLike;
    }

    public void setDireccionLike(String direccionLike) {
        this.direccionLike = direccionLike;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public Long getGanaderoId() {
        return ganaderoId;
    }

    public void setGanaderoId(Long ganaderoId) {
        this.ganaderoId = ganaderoId;
    }
}
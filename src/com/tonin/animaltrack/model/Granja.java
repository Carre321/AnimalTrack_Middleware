package com.tonin.animaltrack.model;

public class Granja extends AbstractValueObject {

    private Long id;
    private String rega;
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private Long municipioId;
    private Long ganaderoId;

    public Granja() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRega() {
        return rega;
    }

    public void setRega(String rega) {
        this.rega = rega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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


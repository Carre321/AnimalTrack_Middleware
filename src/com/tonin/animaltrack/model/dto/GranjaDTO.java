package com.tonin.animaltrack.model.dto;

import com.tonin.animaltrack.model.AbstractValueObject;

public class GranjaDTO extends AbstractValueObject {

    private Long id;
    private String rega;
    private String nombre;
    private String direccion;
    private String codigoPostal;

    private Long municipioId;
    private String municipioNombre;

    private Long provinciaId;
    private String provinciaNombre;

    private Long ganaderoId;
    private String ganaderoNombreCompleto;

    public GranjaDTO() {
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

    public String getMunicipioNombre() {
        return municipioNombre;
    }

    public void setMunicipioNombre(String municipioNombre) {
        this.municipioNombre = municipioNombre;
    }

    public Long getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Long provinciaId) {
        this.provinciaId = provinciaId;
    }

    public String getProvinciaNombre() {
        return provinciaNombre;
    }

    public void setProvinciaNombre(String provinciaNombre) {
        this.provinciaNombre = provinciaNombre;
    }

    public Long getGanaderoId() {
        return ganaderoId;
    }

    public void setGanaderoId(Long ganaderoId) {
        this.ganaderoId = ganaderoId;
    }

    public String getGanaderoNombreCompleto() {
        return ganaderoNombreCompleto;
    }

    public void setGanaderoNombreCompleto(String ganaderoNombreCompleto) {
        this.ganaderoNombreCompleto = ganaderoNombreCompleto;
    }

}

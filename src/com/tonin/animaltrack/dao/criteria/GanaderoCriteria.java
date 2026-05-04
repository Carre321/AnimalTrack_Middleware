package com.tonin.animaltrack.dao.criteria;

public class GanaderoCriteria {

    private Long id;
    private String dni;
    private String dniLike;
    private String nombreLike;
    private String apellidosLike;
    private String telefonoLike;
    private String emailLike;
    private Long municipioId;

    public GanaderoCriteria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDniLike() {
        return dniLike;
    }

    public void setDniLike(String dniLike) {
        this.dniLike = dniLike;
    }

    public String getNombreLike() {
        return nombreLike;
    }

    public void setNombreLike(String nombreLike) {
        this.nombreLike = nombreLike;
    }

    public String getApellidosLike() {
        return apellidosLike;
    }

    public void setApellidosLike(String apellidosLike) {
        this.apellidosLike = apellidosLike;
    }

    public String getTelefonoLike() {
        return telefonoLike;
    }

    public void setTelefonoLike(String telefonoLike) {
        this.telefonoLike = telefonoLike;
    }

    public String getEmailLike() {
        return emailLike;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }
}
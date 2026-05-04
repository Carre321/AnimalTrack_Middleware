package com.tonin.animaltrack.dao.criteria;

public class VeterinarioCriteria {

    private Long id;
    private String codigo;
    private String codigoLike;
    private String dni;
    private String dniLike;
    private String nombreLike;
    private String apellidosLike;
    private String telefonoLike;
    private String emailLike;
    private Long municipioId;
    private Long granjaId;

    public VeterinarioCriteria() {
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

    public Long getGranjaId() {
        return granjaId;
    }

    public void setGranjaId(Long granjaId) {
        this.granjaId = granjaId;
    }
}

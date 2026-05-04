package com.tonin.animaltrack.model.dto;

import com.tonin.animaltrack.model.AbstractValueObject;

public class UsuarioLoginDTO extends AbstractValueObject {

    private Long id;
    private String email;
    private String passwordHash;
    private String rol;
    private Long ganaderoId;
    private String ganaderoNombreCompleto;
    private Long veterinarioId;
    private String veterinarioNombreCompleto;
    private Boolean activo;

    public UsuarioLoginDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public String getVeterinarioNombreCompleto() {
        return veterinarioNombreCompleto;
    }

    public void setVeterinarioNombreCompleto(String veterinarioNombreCompleto) {
        this.veterinarioNombreCompleto = veterinarioNombreCompleto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombreVisible() {
        if (veterinarioNombreCompleto != null && !veterinarioNombreCompleto.trim().isEmpty()) {
            return veterinarioNombreCompleto.trim();
        }
        if (ganaderoNombreCompleto != null && !ganaderoNombreCompleto.trim().isEmpty()) {
            return ganaderoNombreCompleto.trim();
        }
        return email;
    }
}

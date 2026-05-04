package com.tonin.animaltrack.model.dto;

import java.time.LocalDateTime;

import com.tonin.animaltrack.model.AbstractValueObject;

public class NotificacionDTO extends AbstractValueObject {

    private Long id;
    private Long eventoId;

    private String tipo;
    private LocalDateTime fechaEmision;
    private String descripcion;

    private Long tipoNotificacionId;
    private String tipoNotificacionNombre;

    private Long animalId;
    private String animalCrotal;
    private String tipoEventoNombre;

    public NotificacionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getTipoNotificacionId() {
        return tipoNotificacionId;
    }

    public void setTipoNotificacionId(Long tipoNotificacionId) {
        this.tipoNotificacionId = tipoNotificacionId;
    }

    public String getTipoNotificacionNombre() {
        return tipoNotificacionNombre;
    }

    public void setTipoNotificacionNombre(String tipoNotificacionNombre) {
        this.tipoNotificacionNombre = tipoNotificacionNombre;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getAnimalCrotal() {
        return animalCrotal;
    }

    public void setAnimalCrotal(String animalCrotal) {
        this.animalCrotal = animalCrotal;
    }

    public String getTipoEventoNombre() {
        return tipoEventoNombre;
    }

    public void setTipoEventoNombre(String tipoEventoNombre) {
        this.tipoEventoNombre = tipoEventoNombre;
    }

}

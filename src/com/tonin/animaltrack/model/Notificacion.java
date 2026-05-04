package com.tonin.animaltrack.model;

import java.time.LocalDateTime;

public class Notificacion extends AbstractValueObject {

    private Long id;
    private Long eventoId;
    private String tipo;
    private LocalDateTime fechaEmision;
    private String descripcion;
    private Long tipoNotificacionId;

    public Notificacion() {
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
}


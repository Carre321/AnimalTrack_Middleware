package com.tonin.animaltrack.dao.criteria;

import java.time.LocalDateTime;

public class NotificacionCriteria {

    private Long id;
    private Long eventoId;
    private String tipo;
    private LocalDateTime fechaEmisionDesde;
    private LocalDateTime fechaEmisionHasta;
    private String descripcionLike;
    private Long tipoNotificacionId;

    public NotificacionCriteria() {
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

    public LocalDateTime getFechaEmisionDesde() {
        return fechaEmisionDesde;
    }

    public void setFechaEmisionDesde(LocalDateTime fechaEmisionDesde) {
        this.fechaEmisionDesde = fechaEmisionDesde;
    }

    public LocalDateTime getFechaEmisionHasta() {
        return fechaEmisionHasta;
    }

    public void setFechaEmisionHasta(LocalDateTime fechaEmisionHasta) {
        this.fechaEmisionHasta = fechaEmisionHasta;
    }

    public String getDescripcionLike() {
        return descripcionLike;
    }

    public void setDescripcionLike(String descripcionLike) {
        this.descripcionLike = descripcionLike;
    }

    public Long getTipoNotificacionId() {
        return tipoNotificacionId;
    }

    public void setTipoNotificacionId(Long tipoNotificacionId) {
        this.tipoNotificacionId = tipoNotificacionId;
    }
}
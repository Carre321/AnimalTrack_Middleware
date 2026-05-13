package com.tonin.animaltrack.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Evento extends AbstractValueObject {

    private Long id;
    private Long animalId;
    private Long tipoEventoId;
    private Long veterinarioId;
    private LocalDateTime fechaHora;
    private Long semillaId;
    private BigDecimal precioEvento;
    private Long dosisId;
    private Long tratamientoId;
    private String resultado;
    private String observaciones;

    public Evento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getTipoEventoId() {
        return tipoEventoId;
    }

    public void setTipoEventoId(Long tipoEventoId) {
        this.tipoEventoId = tipoEventoId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getSemillaId() {
        return semillaId;
    }

    public void setSemillaId(Long semillaId) {
        this.semillaId = semillaId;
    }

    public BigDecimal getPrecioEvento() {
        return precioEvento;
    }

    public void setPrecioEvento(BigDecimal precioEvento) {
        this.precioEvento = precioEvento;
    }

    public Long getDosisId() {
        return dosisId;
    }

    public void setDosisId(Long dosisId) {
        this.dosisId = dosisId;
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}


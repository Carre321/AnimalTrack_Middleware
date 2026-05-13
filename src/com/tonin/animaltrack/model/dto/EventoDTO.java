package com.tonin.animaltrack.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tonin.animaltrack.model.AbstractValueObject;

public class EventoDTO extends AbstractValueObject {

    private Long id;
    private Long animalId;
    private String animalCrotal;
    private String animalNombre;

    private Long tipoEventoId;
    private String tipoEventoCodigo;
    private String tipoEventoNombre;

    private Long veterinarioId;
    private String veterinarioNombreCompleto;

    private LocalDateTime fechaHora;

    private Long semillaId;
    private String semillaCodigo;

    private BigDecimal precioEvento;

    private Long dosisId;
    private Integer dosisNumOrden;

    private Long tratamientoId;
    private String tratamientoNombre;
    private String resultado;
    private String observaciones;

    public EventoDTO() {
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

    public String getAnimalCrotal() {
        return animalCrotal;
    }

    public void setAnimalCrotal(String animalCrotal) {
        this.animalCrotal = animalCrotal;
    }

    public String getAnimalNombre() {
        return animalNombre;
    }

    public void setAnimalNombre(String animalNombre) {
        this.animalNombre = animalNombre;
    }

    public Long getTipoEventoId() {
        return tipoEventoId;
    }

    public void setTipoEventoId(Long tipoEventoId) {
        this.tipoEventoId = tipoEventoId;
    }

    public String getTipoEventoNombre() {
        return tipoEventoNombre;
    }

    public String getTipoEventoCodigo() {
        return tipoEventoCodigo;
    }

    public void setTipoEventoCodigo(String tipoEventoCodigo) {
        this.tipoEventoCodigo = tipoEventoCodigo;
    }

    public void setTipoEventoNombre(String tipoEventoNombre) {
        this.tipoEventoNombre = tipoEventoNombre;
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

    public String getSemillaCodigo() {
        return semillaCodigo;
    }

    public void setSemillaCodigo(String semillaCodigo) {
        this.semillaCodigo = semillaCodigo;
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

    public Integer getDosisNumOrden() {
        return dosisNumOrden;
    }

    public void setDosisNumOrden(Integer dosisNumOrden) {
        this.dosisNumOrden = dosisNumOrden;
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public String getTratamientoNombre() {
        return tratamientoNombre;
    }

    public void setTratamientoNombre(String tratamientoNombre) {
        this.tratamientoNombre = tratamientoNombre;
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

package com.tonin.animaltrack.dao.criteria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoCriteria {

    private Long id;
    private Long animalId;
    private Long granjaId;
    private String animalNombreLike;
    private String animalCrotalLike;
    private Long tipoEventoId;
    private String tipoEventoCodigo;
    private String tipoEventoNombreLike;
    private Long veterinarioId;
    private String veterinarioNombreLike;
    private Long semillaId;
    private BigDecimal precioEventoDesde;
    private BigDecimal precioEventoHasta;
    private Long dosisId;
    private Long tratamientoId;
    private String resultado;
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;

    public EventoCriteria() {
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

    public Long getGranjaId() {
        return granjaId;
    }

    public void setGranjaId(Long granjaId) {
        this.granjaId = granjaId;
    }

    public String getAnimalNombreLike() {
        return animalNombreLike;
    }

    public void setAnimalNombreLike(String animalNombreLike) {
        this.animalNombreLike = animalNombreLike;
    }

    public String getAnimalCrotalLike() {
        return animalCrotalLike;
    }

    public void setAnimalCrotalLike(String animalCrotalLike) {
        this.animalCrotalLike = animalCrotalLike;
    }

    public Long getTipoEventoId() {
        return tipoEventoId;
    }

    public void setTipoEventoId(Long tipoEventoId) {
        this.tipoEventoId = tipoEventoId;
    }

    public String getTipoEventoCodigo() {
        return tipoEventoCodigo;
    }

    public void setTipoEventoCodigo(String tipoEventoCodigo) {
        this.tipoEventoCodigo = tipoEventoCodigo;
    }

    public String getTipoEventoNombreLike() {
        return tipoEventoNombreLike;
    }

    public void setTipoEventoNombreLike(String tipoEventoNombreLike) {
        this.tipoEventoNombreLike = tipoEventoNombreLike;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public String getVeterinarioNombreLike() {
        return veterinarioNombreLike;
    }

    public void setVeterinarioNombreLike(String veterinarioNombreLike) {
        this.veterinarioNombreLike = veterinarioNombreLike;
    }

    public Long getSemillaId() {
        return semillaId;
    }

    public void setSemillaId(Long semillaId) {
        this.semillaId = semillaId;
    }

    public BigDecimal getPrecioEventoDesde() {
        return precioEventoDesde;
    }

    public void setPrecioEventoDesde(BigDecimal precioEventoDesde) {
        this.precioEventoDesde = precioEventoDesde;
    }

    public BigDecimal getPrecioEventoHasta() {
        return precioEventoHasta;
    }

    public void setPrecioEventoHasta(BigDecimal precioEventoHasta) {
        this.precioEventoHasta = precioEventoHasta;
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

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}

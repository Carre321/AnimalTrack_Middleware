package com.tonin.animaltrack.dao.criteria;

import com.tonin.animaltrack.model.AbstractValueObject;

public class AnimalCriteria extends AbstractValueObject {

	public static final String ORDER_BY_ID = "a.id";
	public static final String ORDER_BY_NAME = "a.nombre";
	public static final String ORDER_BY_CROTAL = "a.crotal";
	public static final String ORDER_BY_RAZA = "r.nombre";
	
    private Long granjaId;
    private Long sexoId;
    private Long razaId;
    private Long madreInternaId;
    private Long padreInternoId;
    private Long eventPartoId;

    private String crotal;
    private String crotalLike;
    private String nombreLike;
    private String orderby = ORDER_BY_NAME;
    private boolean ascDesc = true;

    public AnimalCriteria() {
    }

    public Long getGranjaId() {
        return granjaId;
    }

    public void setGranjaId(Long granjaId) {
        this.granjaId = granjaId;
    }

    public Long getSexoId() {
        return sexoId;
    }

    public void setSexoId(Long sexoId) {
        this.sexoId = sexoId;
    }

    public Long getRazaId() {
        return razaId;
    }

    public void setRazaId(Long razaId) {
        this.razaId = razaId;
    }

    public Long getMadreInternaId() {
        return madreInternaId;
    }

    public void setMadreInternaId(Long madreInternaId) {
        this.madreInternaId = madreInternaId;
    }

    public Long getPadreInternoId() {
        return padreInternoId;
    }

    public void setPadreInternoId(Long padreInternoId) {
        this.padreInternoId = padreInternoId;
    }

    public Long getEventPartoId() {
        return eventPartoId;
    }

    public void setEventPartoId(Long eventPartoId) {
        this.eventPartoId = eventPartoId;
    }

    public String getCrotal() {
        return crotal;
    }

    public void setCrotal(String crotal) {
        this.crotal = crotal;
    }

    public String getCrotalLike() {
        return crotalLike;
    }

    public void setCrotalLike(String crotalLike) {
        this.crotalLike = crotalLike;
    }

    public String getNombreLike() {
        return nombreLike;
    }

    public void setNombreLike(String nombreLike) {
        this.nombreLike = nombreLike;
    }

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public boolean isAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(boolean ascDesc) {
		this.ascDesc = ascDesc;
	}
}

package com.tonin.animaltrack.model;

import java.sql.Date;

public class Animal extends AbstractValueObject {

	private Long id;
	private String nombre;
	private String crotal;
	private Date fechaNacimiento;
	private Date fechaBaja;

	private Long granjaId;
	private Long razaId;
	private Long sexoId;

	private Long madreInternaId;
	private String madreExternaCrotal;
	private Long padreInternoId;

	private Long eventPartoId;

	public Animal() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getCrotal() { return crotal; }
	public void setCrotal(String crotal) { this.crotal = crotal; }

	public Date getFechaNacimiento() { return fechaNacimiento; }
	public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

	public Date getFechaBaja() { return fechaBaja; }
	public void setFechaBaja(Date fechaBaja) { this.fechaBaja = fechaBaja; }

	public Long getGranjaId() { return granjaId; }
	public void setGranjaId(Long granjaId) { this.granjaId = granjaId; }

	public Long getRazaId() { return razaId; }
	public void setRazaId(Long razaId) { this.razaId = razaId; }

	public Long getSexoId() { return sexoId; }
	public void setSexoId(Long sexoId) { this.sexoId = sexoId; }

	public Long getMadreInternaId() { return madreInternaId; }
	public void setMadreInternaId(Long madreInternaId) { this.madreInternaId = madreInternaId; }

	public String getMadreExternaCrotal() { return madreExternaCrotal; }
	public void setMadreExternaCrotal(String madreExternaCrotal) { this.madreExternaCrotal = madreExternaCrotal; }

	public Long getPadreInternoId() { return padreInternoId; }
	public void setPadreInternoId(Long padreInternoId) { this.padreInternoId = padreInternoId; }

	public Long getEventPartoId() { return eventPartoId; }
	public void setEventPartoId(Long eventPartoId) { this.eventPartoId = eventPartoId; }
}


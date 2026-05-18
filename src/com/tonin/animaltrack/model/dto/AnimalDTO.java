package com.tonin.animaltrack.model.dto;

import java.sql.Date;

import com.tonin.animaltrack.model.AbstractValueObject;

public class AnimalDTO extends AbstractValueObject {

	private Long id;
	private String crotal;
	private String nombre;
	private Date fechaNacimiento;
	private Date fechaBaja;

	private Long granjaId;
	private String granjaNombre;

	private Long sexoId;
	private String sexoNombre;

	private Long razaId;
	private String razaNombre;

	private Long madreInternaId;
	private String madreExternaCrotal;

	private Long padreInternoId;
	private String padreInternoNombre;
	
	

	private Long eventPartoId;
	private byte[] foto;

	public AnimalDTO() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCrotal() { return crotal; }
	public void setCrotal(String crotal) { this.crotal = crotal; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public Date getFechaNacimiento() { return fechaNacimiento; }
	public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

	public Date getFechaBaja() { return fechaBaja; }
	public void setFechaBaja(Date fechaBaja) { this.fechaBaja = fechaBaja; }

	public Long getGranjaId() { return granjaId; }
	public void setGranjaId(Long granjaId) { this.granjaId = granjaId; }

	public String getGranjaNombre() { return granjaNombre; }
	public void setGranjaNombre(String granjaNombre) { this.granjaNombre = granjaNombre; }

	public Long getSexoId() { return sexoId; }
	public void setSexoId(Long sexoId) { this.sexoId = sexoId; }

	public String getSexoNombre() { return sexoNombre; }
	public void setSexoNombre(String sexoNombre) { this.sexoNombre = sexoNombre; }

	public Long getRazaId() { return razaId; }
	public void setRazaId(Long razaId) { this.razaId = razaId; }

	public String getRazaNombre() { return razaNombre; }
	public void setRazaNombre(String razaNombre) { this.razaNombre = razaNombre; }

	public Long getMadreInternaId() { return madreInternaId; }
	public void setMadreInternaId(Long madreInternaId) { this.madreInternaId = madreInternaId; }

	public String getMadreExternaCrotal() { return madreExternaCrotal; }
	public void setMadreExternaCrotal(String madreExternaCrotal) { this.madreExternaCrotal = madreExternaCrotal; }

	public Long getPadreInternoId() { return padreInternoId; }
	public void setPadreInternoId(Long padreInternoId) { this.padreInternoId = padreInternoId; }

	public Long getEventPartoId() { return eventPartoId; }
	public void setEventPartoId(Long eventPartoId) { this.eventPartoId = eventPartoId; }

	public String getPadreInternoNombre() {
		return padreInternoNombre;
	}

	public void setPadreInternoNombre(String padreInternoNombre) {
		this.padreInternoNombre = padreInternoNombre;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}

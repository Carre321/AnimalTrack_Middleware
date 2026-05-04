package com.tonin.animaltrack.model;

public class TipoNotificacion extends AbstractValueObject {

    private Long id;
    private String nombre;

    public TipoNotificacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


package com.jdc.web.enums;

public enum PrioridadIncidente {
    BAJA("Baja"),
    MEDIA("Media"),
    ALTA("Alta");

    private final String descripcion;

    PrioridadIncidente(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
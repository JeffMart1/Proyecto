package com.jdc.web.enums;

public enum EstadoIncidente {
    ABIERTO("Abierto"),
    EN_PROGRESO("En Progreso"),
    EN_ESPERA("En Espera"),
    CERRADO("Cerrado");

    private final String descripcion;

    EstadoIncidente(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
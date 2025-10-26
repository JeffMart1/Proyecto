package com.jdc.web.enums;

public enum TipoIncidente {
    HARDWARE("Hardware"),
    SOFTWARE("Software"),
    RED("Red"),
    ACCESOS("Accesos");

    private final String descripcion;

    TipoIncidente(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
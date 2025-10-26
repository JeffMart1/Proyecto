package com.jdc.web.enums;

public enum TecnicoAsignado {
    PEDRO("Pedro"),
    FELIPE("Felipe"),
    JUAN("Juan"),
    PETER("Peter"),
    RAFAEL("Rafael");

    private final String nombre;

    TecnicoAsignado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
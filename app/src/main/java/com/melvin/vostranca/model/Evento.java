package com.melvin.vostranca.model;

public class Evento {

    private String nombre;
    private String fecha;
    private String descripcion;

    public Evento() {
    }

    public Evento(String nombre, String fecha, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

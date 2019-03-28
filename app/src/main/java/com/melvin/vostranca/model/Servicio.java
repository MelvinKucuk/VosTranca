package com.melvin.vostranca.model;

public class Servicio {

    private String nombre;
    private String descripcion;


    public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Servicio(){}

    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }
}

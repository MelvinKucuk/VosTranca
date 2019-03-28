package com.melvin.vostranca.model;

public class Reserva {

    private String nombre;
    private String servicio;
    private Integer telefono;
    private Integer cantidadPersonas;
    private String direccion;
    private String fecha;

    public Reserva() {
    }

    public Reserva(String nombre, String servicio, Integer telefono, Integer cantidadPersonas, String direccion, String fecha) {
        this.nombre = nombre;
        this.servicio = servicio;
        this.telefono = telefono;
        this.cantidadPersonas = cantidadPersonas;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getServicio() {
        return servicio;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFecha() {
        return fecha;
    }
}

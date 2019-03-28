package com.melvin.vostranca.model;

public class Fecha {

    private Integer numero;
    private String mes;
    private String dia;

    public Fecha(Integer numero, String mes, String dia) {
        this.numero = numero;
        this.mes = mes;
        this.dia = dia;
    }

    public Fecha() {
    }

    public Integer getNumero() {
        return numero;
    }

    public String getMes() {
        return mes;
    }

    public String getDia() {
        return dia;
    }
}

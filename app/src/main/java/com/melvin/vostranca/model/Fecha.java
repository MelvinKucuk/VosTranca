package com.melvin.vostranca.model;

public class Fecha {

    private Integer numero;
    private String mes;

    public Fecha(Integer numero, String mes) {
        this.numero = numero;
        this.mes = mes;
    }

    public Fecha() {
    }

    public Integer getNumero() {
        return numero;
    }

    public String getMes() {
        return mes;
    }
}

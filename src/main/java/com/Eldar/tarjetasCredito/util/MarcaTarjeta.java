package com.eldar.tarjetascredito.util;

public enum MarcaTarjeta {
    VISA("VISA"),
    NARA("NARA"),
    AMEX("AMEX");

    private final String marca;

    public String getMarca() {
        return marca;
    }

    MarcaTarjeta(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString(){
        return marca.toUpperCase();
    }
}

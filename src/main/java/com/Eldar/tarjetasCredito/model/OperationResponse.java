package com.eldar.tarjetascredito.model;

import com.eldar.tarjetascredito.util.MarcaTarjeta;

public class OperationResponse {
    private MarcaTarjeta marca;
    private double tasaOperacion;
    private double recargo;
    private double importeInicial;
    private double importeFinal;

    public OperationResponse(MarcaTarjeta marca, double tasaOperacion, double recargo, double importeInicial, double importeFinal) {
        this.marca = marca;
        this.tasaOperacion = tasaOperacion;
        this.recargo = recargo;
        this.importeInicial = importeInicial;
        this.importeFinal = importeFinal;
    }

    public MarcaTarjeta getMarca() {
        return marca;
    }

    public void setMarca(MarcaTarjeta marca) {
        this.marca = marca;
    }

    public double getTasaOperacion() {
        return tasaOperacion;
    }

    public void setTasaOperacion(double tasaOperacion) {
        this.tasaOperacion = tasaOperacion;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getImporteInicial() {
        return importeInicial;
    }

    public void setImporteInicial(double importeInicial) {
        this.importeInicial = importeInicial;
    }

    public double getImporteFinal() {
        return importeFinal;
    }

    public void setImporteFinal(double importeFinal) {
        this.importeFinal = importeFinal;
    }
}
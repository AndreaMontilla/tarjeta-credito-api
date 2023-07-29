package com.eldar.tarjetascredito.model;

import com.eldar.tarjetascredito.exception.ConstantsError;
import com.eldar.tarjetascredito.util.MarcaTarjeta;

import java.time.LocalDate;

import static com.eldar.tarjetascredito.util.TarjetaConstantes.*;

public class TarjetaCredito {

    private final MarcaTarjeta marca;
    private final String numeroTarjeta;
    private final String cardholder;
    private final LocalDate fechaVencimiento;

    public TarjetaCredito(MarcaTarjeta marca, String numeroTarjeta, String cardholder, LocalDate fechaVencimiento) {
        this.marca = marca;
        this.numeroTarjeta = numeroTarjeta;
        this.cardholder = cardholder;
        this.fechaVencimiento = fechaVencimiento;
    }

    public MarcaTarjeta getMarca() {
        return marca;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCardholder() {
        return cardholder;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String obtenerInformacionTarjeta() {
        return "Marca: " + marca.getMarca() + "\nNúmero de Tarjeta: " + numeroTarjeta + "\nCardholder: " + cardholder
                + "\nFecha de Vencimiento: " + fechaVencimiento;
    }

    public boolean esValidaParaOperar() {
        LocalDate fechaActual = LocalDate.now();
        return fechaVencimiento.isAfter(fechaActual);
    }

    public boolean esDistinta(TarjetaCredito otraTarjeta) {
        return !this.numeroTarjeta.equals(otraTarjeta.getNumeroTarjeta());
    }

    public double obtenerTasa() throws IllegalArgumentException {
        double tasa = calcularTasa();
        if (tasa > TASA_MAXIMA) {
            tasa = TASA_MAXIMA;
        } else if (tasa < TASA_MINIMA) {
            tasa = TASA_MINIMA;
        }
        return Math.round(tasa * 10d) / 10d;
    }


    private double calcularTasa() {
        LocalDate fechaActual = LocalDate.now();
        double tasa;
        // Como existe la posibilidad de seguir sumando tarjetas en el futuro, usaremos switch case
        switch (marca) {
            case VISA:
                tasa = (fechaActual.getYear() % 100) / (double) fechaActual.getMonthValue();
                break;
            case NARA:
                tasa = fechaActual.getDayOfMonth() * TASA_NARA;
                break;
            case AMEX:
                tasa = fechaActual.getMonthValue() * TASA_AMEX;
                break;
            // Aca se agregan las tarjetas nuevas
            default:
                throw new IllegalArgumentException(ConstantsError.CODE_ERROR_MISSING_BRAND.getDescripcion());
        }
        return tasa;
    }

}


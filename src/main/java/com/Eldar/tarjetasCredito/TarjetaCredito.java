package com.eldar.tarjetascredito;

import com.eldar.tarjetascredito.exception.ConstantsError;
import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationResponse;
import com.eldar.tarjetascredito.util.MarcaTarjeta;

import java.time.LocalDate;

public class TarjetaCredito {
    public static final double TASA_MAXIMA = 5.0;
    public static final double TASA_MINIMA = 0.3;
    public static final int OPERACION_MAXIMA = 1000;
    public static final int OPERACION_MINIMA = 0;
    // Constantes para las tasas de cada marca
    private static final double TASA_NARA = 0.5;
    private static final double TASA_AMEX = 0.1;

    private MarcaTarjeta marca;
    private String numeroTarjeta;
    private String cardholder;
    private LocalDate fechaVencimiento;

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
        return "Marca: " + marca.getMarca().toUpperCase() + "\nNúmero de Tarjeta: " + numeroTarjeta + "\nCardholder: " + cardholder
                + "\nFecha de Vencimiento: " + fechaVencimiento;
    }

    public boolean esOperacionValida(double montoOperacion) {
        return montoOperacion > OPERACION_MINIMA && montoOperacion < OPERACION_MAXIMA;
    }

    public boolean esValidaParaOperar() {
        LocalDate fechaActual = LocalDate.now();
        return fechaVencimiento.isAfter(fechaActual);
    }

    public boolean esDistinta(TarjetaCredito otraTarjeta) {
        return !this.numeroTarjeta.equals(otraTarjeta.getNumeroTarjeta());
    }

    public double obtenerTasa(MarcaTarjeta marca) throws IllegalArgumentException {
        double tasa = calcularTasa(marca);
        if (tasa > TASA_MAXIMA) {
            tasa = TASA_MAXIMA;
        } else if (tasa < TASA_MINIMA) {
            tasa = TASA_MINIMA;
        }
        return Math.round(tasa * 10d) / 10d;
    }

    public OperationResponse obtenerOperationResponse(double importe, MarcaTarjeta marca) throws TarjetaCreditoException {
        if (!esOperacionValida(importe)) {
            throw new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_AMOUNT);
        }
        double tasa = obtenerTasa(marca);
        double importeFinal = Math.round((importe * (1 + tasa / 100)) * 10d) / 10d;
        double recargo = importeFinal - importe;
        return new OperationResponse(marca, tasa, recargo, importe, importeFinal);
    }

    private double calcularTasa(MarcaTarjeta marca) {
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


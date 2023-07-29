package com.Eldar.tarjetasCredito;

import com.Eldar.tarjetasCredito.model.OperationResponse;
import exception.ConstantsError;
import exception.TarjetaCreditoException;
import java.time.LocalDate;

public class TarjetaCredito {
    private String marca;
    private String numeroTarjeta;
    private String cardholder;
    private LocalDate fechaVencimiento;

    // Constantes para las tasas de cada marca
    private static final double TASA_NARA = 0.5;
    private static final double TASA_AMEX = 0.1;

    public TarjetaCredito(String marca, String numeroTarjeta, String cardholder, LocalDate fechaVencimiento) {
        this.marca = marca;
        this.numeroTarjeta = numeroTarjeta;
        this.cardholder = cardholder;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMarca() {
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
        return "Marca: " + marca + "\nNúmero de Tarjeta: " + numeroTarjeta + "\nCardholder: " + cardholder
                + "\nFecha de Vencimiento: " + fechaVencimiento;
    }

    public boolean esOperacionValida(double montoOperacion) {
        return montoOperacion > 0 && montoOperacion < 1000;
    }
    public boolean esValidaParaOperar() {
        LocalDate fechaActual = LocalDate.now();
        return fechaVencimiento.isAfter(fechaActual);
    }

    public boolean esDistinta(TarjetaCredito otraTarjeta) {
        return !this.numeroTarjeta.equals(otraTarjeta.getNumeroTarjeta());
    }

    public double obtenerTasa(String marca) throws IllegalArgumentException {
        LocalDate fechaActual = LocalDate.now();
        Double tasa;
        // Como existe la posibilidad de seguir sumando tarjetas en el futuro, usaremos switch case
        switch (marca) {
            case "VISA":
                tasa = (fechaActual.getYear()%100) / (double) fechaActual.getMonthValue();
                break;
            case "NARA":
                tasa = fechaActual.getDayOfMonth() * TASA_NARA;
                break;
            case "AMEX":
                tasa = fechaActual.getMonthValue() * TASA_AMEX;
                break;
            // Aca se agregan las tarjetas nuevas
            default:
                throw new IllegalArgumentException(ConstantsError.CODE_ERROR_MISSING_BRAND.getDescripcion());
        }
        if (tasa > 5.0){
            tasa = 5.0;
        } else if (tasa < 0.3){
            tasa = 0.3;
        }
        return Math.round(tasa*10d)/10d;
    }

    public OperationResponse obtenerOperationResponse(double importe, String marca) throws IllegalArgumentException{
        double tasa = obtenerTasa(marca);
        if(!esOperacionValida(importe)){
            throw new IllegalArgumentException(ConstantsError.CODE_ERROR_INVALID_AMOUNT.getDescripcion());
        }
        double importeFinal = Math.round((importe * (1 + tasa/100))*10d) / 10d;
        double recargo = importeFinal - importe;
        return new OperationResponse(marca, tasa, recargo, importe,importeFinal);
    }

}


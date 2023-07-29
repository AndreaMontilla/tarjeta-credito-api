package com.eldar.tarjetascredito.util;

public final class TarjetaConstantes {
    public static final double TASA_MAXIMA = 5.0;
    public static final double TASA_MINIMA = 0.3;
    public static final int OPERACION_MAXIMA = 1000;
    public static final int OPERACION_MINIMA = 0;
    // Constantes para las tasas de cada marca
    public static final double TASA_NARA = 0.5;
    public static final double TASA_AMEX = 0.1;

    private TarjetaConstantes() {
        throw new IllegalStateException("Utility class");
    }
}

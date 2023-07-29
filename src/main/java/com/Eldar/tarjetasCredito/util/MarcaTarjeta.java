package com.eldar.tarjetascredito.util;

import com.eldar.tarjetascredito.exception.ConstantsError;
import com.eldar.tarjetascredito.exception.TarjetaCreditoException;

import java.util.Arrays;

public enum MarcaTarjeta {
    VISA("VISA"),
    NARA("NARA"),
    AMEX("AMEX");

    public String getMarca() {
        return marca;
    }

    private final String marca;

    MarcaTarjeta(String marca) {
        this.marca = marca;
    }

    public static MarcaTarjeta fromString(String marca) throws TarjetaCreditoException {
        return Arrays.stream(MarcaTarjeta.values())
                .filter(marcaTarjeta -> marcaTarjeta.getMarca().equalsIgnoreCase(marca))
                .findFirst()
                .orElseThrow(() -> new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_BRAND));
    }
}

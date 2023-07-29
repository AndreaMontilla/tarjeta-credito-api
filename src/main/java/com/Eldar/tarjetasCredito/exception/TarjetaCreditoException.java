package com.eldar.tarjetascredito.exception;

public class TarjetaCreditoException extends Exception{
    private final String code;

    public TarjetaCreditoException(ConstantsError error) {
        super(error.getDescripcion());
        this.code = error.getCodigo();
    }

    public String getCode() {
        return code;
    }

}
package com.eldar.tarjetascredito.exception;

public class TarjetaCreditoException extends Exception{
    private String code;

    public TarjetaCreditoException(String message, String code) {
        super(message);
        this.code = code;
    }

    public TarjetaCreditoException(ConstantsError error) {
        super(error.getDescripcion());
        this.code = error.getCodigo();
    }

    public String getCode() {
        return code;
    }

}
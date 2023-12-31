package com.eldar.tarjetascredito.exception;

public enum ConstantsError {
    CODE_ERROR_MISSING_BRAND("MISSING_BRAND", "Marca de tarjeta no especificada"),
    CODE_ERROR_INVALID_BRAND("INVALID_BRAND", "Marca de tarjeta inválida"),
    CODE_ERROR_INVALID_AMOUNT("INVALID_AMOUNT", "Importe inválido. Debe ser mayor a 0 y menor a 1000"),
    CODE_ERROR_INVALID_EXPIRATION_DATE("INVALID_EXPIRATION_DATE", "La tarjeta esta vencida");

    private final String codigo;
    private final String descripcion;

    ConstantsError(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public TarjetaCreditoException getError(){
        return new TarjetaCreditoException(this);
    }
}

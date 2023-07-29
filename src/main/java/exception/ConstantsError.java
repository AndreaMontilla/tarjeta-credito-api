package exception;

public enum ConstantsError {
    CODE_ERROR_MISSING_BRAND("MISSING_BRAND", "Marca de tarjeta no especificada"), //BIEN
    CODE_ERROR_INVALID_BRAND("INVALID_BRAND", "Marca de tarjeta inválida"), //bien
    CODE_ERROR_INVALID_AMOUNT("INVALID_AMOUNT", "Importe inválido. Debe ser mayor a 0 y menor a 1000"), //BIEN
    CODE_ERROR_BAD_REQUEST("BAD_REQUEST", "Error en la solicitud");

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
}

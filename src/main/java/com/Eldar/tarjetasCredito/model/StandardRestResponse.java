package com.eldar.tarjetascredito.model;

import com.eldar.tarjetascredito.exception.TarjetaCreditoException;

public class StandardRestResponse<OperationResponse> {
    private OperationResponse data;
    private TarjetaCreditoException error;

    public StandardRestResponse(OperationResponse data) {
        this.data = data;
    }

    public StandardRestResponse(TarjetaCreditoException error) {
        this.error = error;
    }

    public OperationResponse getData() {
        return data;
    }

    public void setData(OperationResponse data) {
        this.data = data;
    }

    public TarjetaCreditoException getError() {
        return error;
    }

    public void setError(TarjetaCreditoException error) {
        this.error = error;
    }
}
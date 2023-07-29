package com.eldar.tarjetascredito.model;

public class StandardRestResponse<T, E> {
    private T data;
    private E error;

    public StandardRestResponse(T data, E error) {
        this.data = data;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public E getError() {
        return error;
    }

    public void setError(E error) {
        this.error = error;
    }
}
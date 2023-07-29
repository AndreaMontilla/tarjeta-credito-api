package com.eldar.tarjetascredito.service;

import com.eldar.tarjetascredito.model.TarjetaCredito;
import com.eldar.tarjetascredito.exception.ConstantsError;
import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationResponse;
import org.springframework.stereotype.Service;

import static com.eldar.tarjetascredito.util.TarjetaConstantes.OPERACION_MAXIMA;
import static com.eldar.tarjetascredito.util.TarjetaConstantes.OPERACION_MINIMA;

@Service
public class TarjetaCreditoService {

    public OperationResponse obtenerOperationResponse(TarjetaCredito tarjeta, double importe) throws TarjetaCreditoException {
        validarFecha(tarjeta);
        validarImporte(importe);
        return obtenerOperationResponse(importe, tarjeta);
    }

    private void validarFecha(TarjetaCredito tarjeta) throws TarjetaCreditoException {
        if (!tarjeta.esValidaParaOperar()) {
            throw new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_EXPIRATION_DATE);
        }
    }

    private void validarImporte(double importe) throws TarjetaCreditoException {
        if (!esOperacionValida(importe)) {
            throw new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_AMOUNT);
        }
    }

    private OperationResponse obtenerOperationResponse(double importe, TarjetaCredito tarjetaCredito) {
        double tasa = tarjetaCredito.obtenerTasa();
        double importeFinal = Math.round((importe * (1 + tasa / 100)) * 10d) / 10d;
        double recargo = importeFinal - importe;
        return new OperationResponse(tarjetaCredito.getMarca(), tasa, recargo, importe, importeFinal);
    }

    public boolean esOperacionValida(double montoOperacion) {
        return montoOperacion > OPERACION_MINIMA && montoOperacion < OPERACION_MAXIMA;
    }
}
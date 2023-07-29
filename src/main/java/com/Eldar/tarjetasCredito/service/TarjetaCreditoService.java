package com.eldar.tarjetascredito.service;

import com.eldar.tarjetascredito.TarjetaCredito;
import com.eldar.tarjetascredito.exception.ConstantsError;
import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationRequest;
import com.eldar.tarjetascredito.model.OperationResponse;
import com.eldar.tarjetascredito.util.MarcaTarjeta;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class TarjetaCreditoService {

    private static final int OPERACION_MINIMA = 0;
    private static final int OPERACION_MAXIMA = 1000;

    public OperationResponse obtenerOperationResponse(String marcaString, double importe) throws TarjetaCreditoException {
        validarMarcaTarjeta(marcaString);
        validarImporte(importe);

        TarjetaCredito tarjeta = new TarjetaCredito(MarcaTarjeta.fromString(marcaString), "", "", LocalDate.now());
        return tarjeta.obtenerOperationResponse(importe, tarjeta.getMarca());
    }

    private void validarMarcaTarjeta(String marca) throws TarjetaCreditoException {
        if (marca == null || marca.trim().isEmpty()) {
            throw new TarjetaCreditoException(ConstantsError.CODE_ERROR_MISSING_BRAND);
        }

        if (Arrays.stream(MarcaTarjeta.values()).noneMatch(marcaTarjeta -> marcaTarjeta.getMarca().equalsIgnoreCase(marca))) {
            throw new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_BRAND);
        }
    }

    private void validarImporte(double importe) throws TarjetaCreditoException {
        if (importe <= OPERACION_MINIMA || importe > OPERACION_MAXIMA) {
            throw new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_AMOUNT);
        }
    }
}
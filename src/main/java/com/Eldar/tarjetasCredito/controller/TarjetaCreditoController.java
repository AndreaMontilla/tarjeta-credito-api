/*
package com.eldar.tarjetascredito.controller;

import com.eldar.tarjetascredito.TarjetaCredito;
import com.eldar.tarjetascredito.exception.ConstantsError;
import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationRequest;
import com.eldar.tarjetascredito.model.OperationResponse;
import com.eldar.tarjetascredito.model.StandardRestResponse;
import com.eldar.tarjetascredito.util.MarcaTarjeta;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;

import static com.eldar.tarjetascredito.TarjetaCredito.OPERACION_MAXIMA;
import static com.eldar.tarjetascredito.TarjetaCredito.OPERACION_MINIMA;
import static com.eldar.tarjetascredito.util.MarcaTarjeta.*;

@RestController
@Validated
public class TarjetaCreditoController {

    @PostMapping("/tasa-operacion")
    public ResponseEntity<StandardRestResponse<OperationResponse>> obtenerTasaOperacion(@RequestBody OperationRequest operationRequest) {
        // Validar si la marca está presente
        if (operationRequest.getMarca() == null || operationRequest.getMarca().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new StandardRestResponse<>(ConstantsError.CODE_ERROR_MISSING_BRAND.getError()));
        }

        try {
            // Realizar validación para marca (permitir solo marcas válidas utilizando las constantes)
            if (Arrays.stream(values()).noneMatch(marcaTarjeta -> marcaTarjeta.getMarca().equals(operationRequest.getMarca()))) {
                return ResponseEntity.badRequest().body(new StandardRestResponse<>(ConstantsError.CODE_ERROR_INVALID_BRAND.getError()));
            }

            // Realizar validación para importe (permitir solo importes mayores a 0 y menores a 1000)
            if (operationRequest.getImporte() <= OPERACION_MINIMA || operationRequest.getImporte() > OPERACION_MAXIMA) {
                return ResponseEntity.badRequest().body(new StandardRestResponse<>(ConstantsError.CODE_ERROR_INVALID_AMOUNT.getError()));
            }

            // Obtener la tasa de la operación utilizando la clase TarjetaCredito
            TarjetaCredito tarjeta = new TarjetaCredito(MarcaTarjeta.fromString(operationRequest.getMarca()), "", "", LocalDate.now());
            OperationResponse operationResponse = tarjeta.obtenerOperationResponse(operationRequest.getImporte(), tarjeta.getMarca());
            return ResponseEntity.ok(new StandardRestResponse<>(operationResponse));

        } catch (IllegalArgumentException | TarjetaCreditoException illegalArgumentException) {
            return ResponseEntity.badRequest().body(new StandardRestResponse<>(ConstantsError.CODE_ERROR_BAD_REQUEST.getError()));
        }
    }
}*/

package com.eldar.tarjetascredito.controller;

import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationRequest;
import com.eldar.tarjetascredito.model.OperationResponse;
import com.eldar.tarjetascredito.model.StandardRestResponse;
import com.eldar.tarjetascredito.service.TarjetaCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TarjetaCreditoController {

    private final TarjetaCreditoService tarjetaCreditoService;

    @Autowired
    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @PostMapping("/tasa-operacion")
    public ResponseEntity<StandardRestResponse<OperationResponse>> obtenerTasaOperacion(@RequestBody OperationRequest operationRequest) {
        try {

            OperationResponse operationResponse = tarjetaCreditoService.obtenerOperationResponse(operationRequest.getMarca(), operationRequest.getImporte());
            return ResponseEntity.ok(new StandardRestResponse<>(operationResponse));

        } catch (TarjetaCreditoException tarjetaCreditoException) {
            return ResponseEntity.badRequest().body(new StandardRestResponse<>(tarjetaCreditoException));
        }
    }
}




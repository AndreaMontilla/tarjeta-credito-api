package com.Eldar.tarjetasCredito.controller;

import com.Eldar.tarjetasCredito.TarjetaCredito;
import com.Eldar.tarjetasCredito.model.OperationRequest;
import com.Eldar.tarjetasCredito.model.OperationResponse;
import com.Eldar.tarjetasCredito.model.StandardRestResponse;
import exception.ConstantsError;
import exception.TarjetaCreditoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import util.TarjetaConstantes;

import java.time.LocalDate;

@RestController
@Validated
public class TarjetaCreditoController {

    @PostMapping("/tasa-operacion")
    public ResponseEntity<StandardRestResponse<OperationResponse>> obtenerTasaOperacion(@RequestBody OperationRequest operationRequest) {
        // Validar si la marca está presente
        if (operationRequest.getMarca() == null || operationRequest.getMarca().trim().isEmpty()) {
            TarjetaCreditoException exception = new TarjetaCreditoException(ConstantsError.CODE_ERROR_MISSING_BRAND.getDescripcion(), ConstantsError.CODE_ERROR_MISSING_BRAND.getCodigo());
            return ResponseEntity.badRequest().body(new StandardRestResponse<OperationResponse>(exception));
        }

        try {
            // Realizar validación para marca (permitir solo marcas válidas utilizando las constantes)
            if (!TarjetaConstantes.VISA.equals(operationRequest.getMarca()) && !TarjetaConstantes.NARA.equals(operationRequest.getMarca()) &&
                    !TarjetaConstantes.AMEX.equals(operationRequest.getMarca())) {
                TarjetaCreditoException exception = new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_BRAND.getDescripcion(), ConstantsError.CODE_ERROR_INVALID_BRAND.getCodigo());
                return ResponseEntity.badRequest().body(new StandardRestResponse<>(exception));
            }

            // Realizar validación para importe (permitir solo importes mayores a 0 y menores a 1000)
            if (operationRequest.getImporte() <= 0 || operationRequest.getImporte() > 1000) {
                TarjetaCreditoException exception = new TarjetaCreditoException(ConstantsError.CODE_ERROR_INVALID_AMOUNT.getDescripcion(), ConstantsError.CODE_ERROR_INVALID_AMOUNT.getCodigo());
                return ResponseEntity.badRequest().body(new StandardRestResponse<>(exception));
            }

            // Obtener la tasa de la operación utilizando la clase TarjetaCredito
            TarjetaCredito tarjeta = new TarjetaCredito(operationRequest.getMarca(), "", "", LocalDate.now());
            OperationResponse operationResponse = tarjeta.obtenerOperationResponse(operationRequest.getImporte(), tarjeta.getMarca());
            return ResponseEntity.ok(new StandardRestResponse<>(operationResponse));


        } catch (IllegalArgumentException illegalArgumentException) {
            TarjetaCreditoException exception = new TarjetaCreditoException(ConstantsError.CODE_ERROR_BAD_REQUEST.getDescripcion(), ConstantsError.CODE_ERROR_BAD_REQUEST.getCodigo());
            return ResponseEntity.badRequest().body(new StandardRestResponse<>(exception));
        }
    }
}
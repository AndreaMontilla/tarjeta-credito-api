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
    public ResponseEntity<StandardRestResponse<OperationResponse,TarjetaCreditoException>> obtenerTasaOperacion(@RequestBody OperationRequest request) {
        try {

            OperationResponse response = tarjetaCreditoService.obtenerOperationResponse(request.getTarjeta(), request.getImporte());
            return ResponseEntity.ok()
                    .body(new StandardRestResponse<>(response, null));

        } catch (TarjetaCreditoException tarjetaCreditoException) {
            return ResponseEntity.badRequest()
                    .body(new StandardRestResponse<>(null, tarjetaCreditoException));
        }
    }
}




package com.eldar.tarjetascredito;

import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationResponse;
import com.eldar.tarjetascredito.util.MarcaTarjeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        final Logger log = LoggerFactory.getLogger(Main.class);

        TarjetaCredito tarjeta1 = new TarjetaCredito(MarcaTarjeta.VISA, "1234-5678-9012-3456", "Juan Pérez",
                LocalDate.of(2025, 7, 31));
        TarjetaCredito tarjeta2 = new TarjetaCredito(MarcaTarjeta.NARA, "9876-5432-1098-7654", "María García",
                LocalDate.of(2024, 9, 15));
        TarjetaCredito tarjeta3 = new TarjetaCredito(MarcaTarjeta.AMEX, "4567-8901-2345-6789", "Carlos Rodríguez",
                LocalDate.of(2023, 12, 10));

        // Información de tarjetas
        log.info("Información de tarjeta 1: {}", tarjeta1.obtenerInformacionTarjeta());

        // Operación válida
        double importeOperacion = 500; // Ejemplo de importe de la operación
        log.info("Operación válida para tarjeta 2: {}", tarjeta2.esOperacionValida(importeOperacion));

        // Tarjeta válida para operar
        log.info("Tarjeta 3 válida para operar: {}", tarjeta3.esValidaParaOperar());

        // Identificar si una tarjeta es distinta a otra
        log.info("Tarjeta 1 es distinta a Tarjeta 2: {}", tarjeta1.esDistinta(tarjeta2));
        log.info("Tarjeta 1 es distinta a Tarjeta 3: {}", tarjeta1.esDistinta(tarjeta3));

        // Obtener tasa de una operación
        try {
            double importe = 600;
            OperationResponse respuestaOperacion = tarjeta1.obtenerOperationResponse(importe, tarjeta1.getMarca()); // Ejemplo de importe de la operación
            log.info("Su tarjeta {} tiene una tasa de {} por lo que el importe final es de {}", tarjeta1.getMarca(), respuestaOperacion.getTasaOperacion(), respuestaOperacion.getImporteFinal());
        } catch (TarjetaCreditoException e){
            log.error("Se produjo un error: {}. Con codigo: {}", e.getMessage(), e.getCode());
        }
    }
}

package com.eldar.tarjetascredito;

import com.eldar.tarjetascredito.exception.TarjetaCreditoException;
import com.eldar.tarjetascredito.model.OperationResponse;
import com.eldar.tarjetascredito.model.TarjetaCredito;
import com.eldar.tarjetascredito.service.TarjetaCreditoService;
import com.eldar.tarjetascredito.util.MarcaTarjeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class ClaseEjecutable {
    private static final Logger log = LoggerFactory.getLogger(ClaseEjecutable.class);
    private static final TarjetaCreditoService service = new TarjetaCreditoService();

    public static void main(String[] args) {

        TarjetaCredito tarjeta1 = new TarjetaCredito(MarcaTarjeta.VISA, "1234-5678-9012-3456", "Juan Pérez",
                LocalDate.of(2025, 7, 31));
        TarjetaCredito tarjeta2 = new TarjetaCredito(MarcaTarjeta.NARA, "9876-5432-1098-7654", "María García",
                LocalDate.of(2021, 9, 15));
        TarjetaCredito tarjeta3 = new TarjetaCredito(MarcaTarjeta.AMEX, "4567-8901-2345-6789", "Carlos Rodríguez",
                LocalDate.of(2023, 12, 10));
        TarjetaCredito tarjeta4 = new TarjetaCredito(MarcaTarjeta.VISA, "1234-5678-9012-3456", "Juan Pérez",
                LocalDate.of(2025, 7, 31));

        // Información de tarjetas
        String descripcion = tarjeta1.obtenerInformacionTarjeta();
        log.info("Información de tarjeta 1: {}", descripcion);

        double importeOperacionValida = 500; // Ejemplo de importe de la operación
        double importeOperacionInvalida = 1500;
        // Operación válida
        log.info("Operación por {} es valida: {}", importeOperacionValida, service.esOperacionValida(importeOperacionValida));
        // Operación inválida
        log.info("Operación por {} es valida: {}", importeOperacionInvalida, service.esOperacionValida(importeOperacionInvalida));

        // Tarjeta válida para operar
        log.info("Tarjeta 3, de fecha {}, es válida para operar: {}", tarjeta3.getFechaVencimiento(), tarjeta3.esValidaParaOperar());
        // Tarjeta inválida para operar
        log.info("Tarjeta 2, de fecha {}, es válida para operar: {}", tarjeta2.getFechaVencimiento(), tarjeta2.esValidaParaOperar());

        // Identificar si una tarjeta es distinta a otra
        log.info("Tarjeta 1 es distinta a Tarjeta 2: {}", tarjeta1.esDistinta(tarjeta2));
        log.info("Tarjeta 1 es distinta a Tarjeta 3: {}", tarjeta1.esDistinta(tarjeta3));
        log.info("Tarjeta 1 es distinta a Tarjeta 4: {}", tarjeta1.esDistinta(tarjeta4));

        // Obtener tasa de una operación exitosa
        operar(tarjeta1, 600);
        // Operacion fallida por fecha de expiracion
        operar(tarjeta2, 600);
        // Operacion fallida por monto
        operar(tarjeta1, 6000);
    }

    private static void operar(TarjetaCredito tarjetaCredito, double importe) {
        log.info("Se intentara operar tarjeta {} de fecha {} por el monto {}", tarjetaCredito.getMarca(), tarjetaCredito.getFechaVencimiento(), importe );
        try {
            OperationResponse respuestaOperacion = service.obtenerOperationResponse(tarjetaCredito, importe);
            log.info("Su tarjeta {} tiene una tasa de {} por lo que el importe final es de {}", tarjetaCredito.getMarca(), respuestaOperacion.getTasaOperacion(), respuestaOperacion.getImporteFinal());
        } catch (TarjetaCreditoException e){
            log.error("Se produjo un error: {}. Con codigo: {}", e.getMessage(), e.getCode());
        }
    }
}

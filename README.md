# Proyecto Tarjetas de Crédito

Este proyecto es una aplicación para procesar operaciones con tarjetas de crédito para una organización. Proporciona un módulo que permite realizar diversas operaciones con tarjetas, como verificar su validez, obtener información de una tarjeta y calcular la tasa para una operación.

## Requisitos

- Java 8 o superior
- Maven
- Servidor web (por ejemplo, Apache Tomcat) para ejecutar la API REST (solo necesario para la Parte 2 del ejercicio)

## Instrucciones para ejecutar el proyecto

A continuación, se detallan los pasos para ejecutar la aplicación:

### Paso 1: Compilación

Desde la línea de comandos, navega hasta la carpeta raíz del proyecto y ejecuta el siguiente comando para compilar el proyecto con Maven:

###### mvn clean package

### Paso 2: Ejecución de la Clase ClaseEjecutable (Ejercicio 1)

Para probar la funcionalidad de la clase `ClaseEjecutable` y los métodos implementados, ejecuta la clase `ClaseEjecutable` desde la carpeta raíz del proyecto:

###### java -cp target/tarjetas-credito.jar com.tuorganizacion.tarjetascredito.ClaseEjecutable

Deberías ver la salida en la consola que muestra información de tarjetas, validaciones y tasas para diferentes casos.

### Paso 3: Ejecución de la API REST (Ejercicio 2)

Si deseas ejecutar la API REST y probarla, asegúrate de tener un servidor web (por ejemplo, Apache Tomcat) configurado para ejecutar la aplicación. Luego, despliega el archivo JAR generado en el servidor.

¡Gracias por utilizar el proyecto Tarjetas de Crédito!

### Posibles mejoras:

- Implementar el uso de Lombok.
- Generar swagger para documentacion del proyecto.
- Implementacion de tests unitarios con Junit y Mockito.
- Agregar endpoint POST que solo reciba marca de tarjeta e importe y regrese unicamente importe final de la transaccion y tasa de interes (detectado como solicitado en la prueba tecnica, por tema tiempo no puede ser realizado en este momento).

### Ejercicio 5

Para ejecutar el ejercicio 5 se creo la función solicitada en el archivo Ejercicio5.java, con la finalidad de poder probarlo con facilidad se agregaron algunos casos de prueba. Dicha clase se ejecuta de la misma forma que la ClaseEjecutable, tal como se indico en el paso 2 del presente archivo.
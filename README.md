# Proyecto Tarjetas de Crédito

Este proyecto es una aplicación para procesar operaciones con tarjetas de crédito para una organización. Proporciona un módulo que permite realizar diversas operaciones con tarjetas, como verificar su validez, obtener información de una tarjeta y calcular la tasa para una operación.

## Requisitos

- Java 8 o superior
- Maven
- Servidor web (por ejemplo, Apache Tomcat) para ejecutar la API REST (solo necesario para la Parte 2 del ejercicio)

## Instrucciones para Ejecutar el Proyecto

A continuación, se detallan los pasos para ejecutar la aplicación:

### Paso 1: Compilación

Desde la línea de comandos, navega hasta la carpeta raíz del proyecto y ejecuta el siguiente comando para compilar el proyecto con Maven:

###### mvn clean package

### Paso 2: Ejecución de la Clase Main (Ejercicio 1)

Para probar la funcionalidad de la clase `TarjetaCredito` y los métodos implementados, ejecuta la clase `Main` desde la carpeta raíz del proyecto:

###### java -cp target/tarjetas-credito.jar com.tuorganizacion.tarjetascredito.Main

Deberías ver la salida en la consola que muestra información de tarjetas, validaciones y tasas para diferentes casos.

### Paso 3: Ejecución de la API REST (Ejercicio 2)

Si deseas ejecutar la API REST y probarla, asegúrate de tener un servidor web (por ejemplo, Apache Tomcat) configurado para ejecutar la aplicación. Luego, despliega el archivo JAR generado en el servidor.

¡Gracias por utilizar el proyecto Tarjetas de Crédito!
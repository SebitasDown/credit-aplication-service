# Servicio de Solicitud de Crédito (Credit Application Service)

Este microservicio se encarga de gestionar y procesar las solicitudes de crédito. Actúa como el orquestador principal, recibiendo solicitudes de los clientes, comunicándose con la central de riesgos y determinando la viabilidad del crédito.

## Características

*   **Procesamiento de Solicitudes**: Recibe datos del cliente (documento, monto, plazo).
*   **Integración con Central de Riesgos**: Se comunica con el servicio `risk-central-mock-service` para evaluar el historial crediticio.
*   **Decisión de Crédito**: Basado en el `score` y nivel de riesgo retornado, aprueba o rechaza la solicitud.
*   **Persistencia**: Guarda el historial de las evaluaciones realizadas (MySQL).

## Tecnologías

*   **Java 17+**
*   **Spring Boot v3.5.8**
*   **Spring WebFlux (WebClient)**: Para la comunicación reactiva con el servicio de riesgos.
*   **Spring Data JPA**: Para la persistencia de datos.
*   **MySQL**: Base de datos relacional.
*   **Docker**: Para la contenedorización.

## Configuración

### Variables de Entorno

El servicio utiliza las siguientes variables importantes:

*   `RISK_CENTRAL_URL`: URL base del servicio de riesgos (por defecto `http://localhost:8081`).
*   `SPRING_DATASOURCE_URL`: URL de conexión a la base de datos MySQL.

### Ejecución Local

1.  Asegúrate de tener la base de datos MySQL corriendo y configurada en [application.properties](cci:7://file:///home/sebitas/Escritorio/docker/risk-central-mock-service/src/main/resources/application.properties:0:0-0:0).
2.  Asegúrate de que el servicio de riesgos (`risk-central-mock-service`) esté en ejecución.
3.  Ejecuta el proyecto con Maven:

    ```bash
    ./mvnw spring-boot:run
    ```

### API Endpoints

#### Solicitar Crédito

*   **URL**: `/api/credit/request`
*   **Método**: `POST`
*   **Body**:

    ```json
    {
      "document": "12345678",
      "amount": 5000.00,
      "term": 24
    }
    ```

*   **Respuesta Exitosa (200 OK)**:

    ```json
    {
      "evaluationId": 1,
      "status": "APPROVED",
      "riskLevel": "BAJO RIESGO",
      "message": "Solicitud aprobada correctamente."
    }
    ```

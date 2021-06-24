# api-taxi24
# API TAXI 24 ## Requisitos - Maven - JDK8+ - JEE - MySQL - Apache Tomcat


# API TAXI 24
## Requisitos
- Maven
- JDK8+
- JEE
- MySQL
- Apache Tomcat

## Configuración
**1. Configurar base de datos en el siguiente archivo:**
*src/main/java/com/taxi/properties/database.properties*

**2. Ejecutar el script de base datos(MySQL):**
*sql/script.sql*

**3. Generar war utilizando Maven:**
 1. mvn clean
 2. mvn compile
 3. mvn package

**4. Subir war a servidor de Tomcat**

## Endpoint's

**Obtener pasajeros**
GET /api-taxi24/api/taxi/pasajero/obtener/ HTTP/1.1
Host: localhost:8085

**Obtener pasajeros por id**
GET /api-taxi24/api/taxi/pasajero/obtener/info HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 15

{
    "id": 1
}

**Obtener conductores por dispobilidad e ignorando la disponibilidad**
GET /api-taxi24/api/taxi/conductor/obtener HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 30

{
    "disponibilidad": true
}

**Obtener conductor por Id**
GET /api-taxi24/api/taxi/conductor/obtener/info HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 15

{
    "id": 1
}

**Obtener conductores en base a ubicación cercana de 3km**
GET /api-taxi24/api/taxi/conductor/obtener/ubicacion HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 74

{
    "latitud": 19.193549704296345
    , "longitud": -96.16116438873652
}

**Obtener viajes activos**
GET /api-taxi24/api/taxi/viaje/obtener HTTP/1.1
Host: localhost:8085

**Crear viaje**
POST /api-taxi24/api/taxi/viaje/crear HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 95

{
    "idPasajero": 9
    , "latitud": 19.17135154175434
    , "longitud": -96.17521405779753
}

**Asociar conductor a viaje**
POST /api-taxi24/api/taxi/viaje/asociar/conductor HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 38

{
    "id": 1
    , "idConductor": 9
}

**Terminar viaje**
POST /api-taxi24/api/taxi/viaje/terminar HTTP/1.1
Host: localhost:8085
Content-Type: application/json
Content-Length: 15

{
    "id": 1
}


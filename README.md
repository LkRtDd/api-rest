# Facturación REST API

Este proyecto es una API REST para la gestión de facturas, clientes y productos. Está construido con Spring Boot y utiliza una base de datos H2 en memoria.

## Índice

- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
  - [Clientes](#clientes)
  - [Productos](#productos)
  - [Facturas](#facturas)
- [Configuración](#configuración)
- [Documentación](#documentación)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Requisitos

- Java 17
- Maven

## Instalación

1. Clona el repositorio:

   ```sh
   git clone <URL_DEL_REPOSITORIO>
   cd facturacionrest
   ```

2. Compila y empaqueta el proyecto:

   ```sh
   ./mvnw clean package
   ```

3. Ejecuta la aplicación:
   ```sh
   ./mvnw spring-boot:run
   ```

## Uso

La API expone los siguientes endpoints:

### Clientes

- **GET /clientes/all**: Obtener todos los clientes.
- **GET /clientes/cliente/{id}**: Obtener un cliente por ID.
- **POST /clientes/crearcliente**: Crear un nuevo cliente.
- **PUT /clientes/cliente/{id}**: Actualizar un cliente existente.
- **DELETE /clientes/cliente/{id}**: Eliminar un cliente por ID.

### Productos

- **GET /productos**: Obtener todos los productos.
- **GET /productos/{id}**: Obtener un producto por ID.
- **POST /productos**: Crear un nuevo producto.
- **PUT /productos/{id}**: Actualizar un producto existente.
- **DELETE /productos/{id}**: Eliminar un producto por ID.

### Facturas

- **POST /facturas?clienteId={id}**: Crear una nueva factura para un cliente.

## Configuración

La configuración de la base de datos H2 se encuentra en el archivo [application.properties](src/main/resources/application.properties).

```
properties
spring.application.name=facturacionrest
server.port=8082
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
springdoc.swagger-ui.path=/swagger-ui.html
```

## Documentación

La documentación de la API esta disponible en /swagger-ui.html

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request.

## Licencia

Este proyecto está licenciado bajo la Licencia Apache 2.0. Consulta el archivo [LICENSE](mvnw) para más detalles.

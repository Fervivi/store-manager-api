# Store Manager Microservice (store-manager-api v1)

## Descripción

Microservicio encargado de coordinar operaciones de tienda dentro del ecosistema de microservicios del proyecto FullStack 1.

Este servicio funciona como orquestador, comunicándose con el microservicio de productos y el microservicio de carritos mediante `WebClient`.

Permite crear, consultar, actualizar y eliminar carritos, además de validar productos disponibles antes de utilizarlos en operaciones de tienda.

---

## Tech Stack

### Infraestructura

* Java 25
* Spring Boot 4.0.6
* Docker
* Docker Compose
* MySQL
* Maven

### Dependencias principales

1. Lombok
2. Spring Web MVC
3. Spring WebFlux
4. Spring Security
5. JWT
6. Spring Data JPA
7. Validation
8. MySQL Connector
9. Flyway
10. SpringDoc OpenAPI / Swagger

---

## Responsabilidad del microservicio

El Store Manager no administra directamente productos ni carritos en base de datos.

Su función principal es coordinar operaciones entre microservicios externos.

### Microservicios consumidos

| Microservicio        | Función                                                     |
| -------------------- | ----------------------------------------------------------- |
| Product Microservice | Consulta y validación de productos                          |
| Carts Microservice   | Creación, consulta, actualización y eliminación de carritos |

---

## API / Endpoints

Base URL:

```txt
/api/v1/store-manager
```

| Acción                | Método | Endpoint                                      |
| --------------------- | ------ | --------------------------------------------- |
| Verificar estado      | GET    | `/api/v1/store-manager/health`                        |
| Crear carrito         | POST   | `/api/v1/store-manager/carts`                         |
| Listar carritos       | GET    | `/api/v1/store-manager/carts`                         |
| Buscar carrito por ID | GET    | `/api/v1/store-manager/carts/{cartId}`                |
| Actualizar carrito    | PUT    | `/api/v1/store-manager/carts/{cartId}`                |
| Eliminar carrito      | DELETE | `/api/v1/store-manager/carts/{cartId}`                |
| Validar producto      | GET    | `/api/v1/store-manager/products/{productId}/validate` |

---

## Ejemplos de uso

### Crear carrito

```http
POST http://localhost:8010/api/v1/store-manager/carts
```

Headers:

```txt
Authorization: Bearer TOKEN
Content-Type: application/json
```

Body:

```json
{
  "customerId": 1
}
```

---

### Obtener carrito

```http
GET http://localhost:8010/api/v1/store-manager/carts/1
```

---

### Validar producto

```http
GET http://localhost:8010/api/v1/store-manager/products/1/validate
```

---

## Variables de entorno

```env
SPRING_ENV=dev
SPRING_APP_NAME=StoreManager

HOST_PORT=8010

PRODUCTS_API_BASE_URL=http://localhost:8005/api/v1/products
CARTS_API_BASE_URL=http://localhost:8002/api/v1/carts

SPRING_JWT_SECRET=secret-key
SPRING_JWT_ISSUER=login-service
```

---

## Ejecución

### Levantar contenedores

```bash
docker compose up -d
```

### Ejecutar aplicación

```bash
mvn spring-boot:run
```

---

## Autenticación

Los endpoints protegidos requieren JWT.

Header:

```txt
Authorization: Bearer TOKEN
```

---

## Documentacion Swagger / OpenAPI

En ambiente `dev`, la documentacion interactiva queda disponible en:

```txt
http://localhost:8010/swagger-ui/index.html
```

El contrato OpenAPI en formato JSON queda disponible en:

```txt
http://localhost:8010/v3/api-docs
```

Para probar endpoints protegidos desde Swagger UI, usar el boton **Authorize** e ingresar el JWT con formato:

```txt
Bearer TOKEN
```

En ambiente `prod`, Swagger y `/v3/api-docs` estan deshabilitados desde `application-prod.properties`.

---

## Arquitectura

Store Manager actúa como orquestador entre:

* Product Microservice
* Carts Microservice

La comunicación entre microservicios se realiza mediante `WebClient`.

---

## Equipo

* Eduardo Bray
* Rodrigo Callealta
* Fernando Villalobos

> DuocUC — FullStack 1 © 2026

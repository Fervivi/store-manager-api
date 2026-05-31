2. Store Manager Microservice
Descripción

El microservicio Store Manager funciona como orquestador entre otros microservicios.
Su objetivo es coordinar operaciones relacionadas con carritos y productos, comunicándose con los microservicios de Carts y Product mediante WebClient.

Funcionalidades principales
Crear carritos mediante el MS Carts.
Listar carritos.
Buscar carrito por ID.
Actualizar carrito.
Eliminar carrito.
Validar productos consultando el MS Product.
Verificar si un producto existe, está activo y tiene stock disponible.
Enviar token JWT hacia los microservicios dependientes.
Endpoints principales
GET /api/v1/store/health

Verifica que Store Manager esté funcionando.

POST /api/v1/store/carts

Crea un carrito.

Ejemplo de body:

{
  "customerId": 1
}
GET /api/v1/store/carts

Lista todos los carritos.

GET /api/v1/store/carts/{cartId}

Busca un carrito por ID.

PUT /api/v1/store/carts/{cartId}

Actualiza el cliente asociado a un carrito.

Ejemplo de body:

{
  "customerId": 2
}
DELETE /api/v1/store/carts/{cartId}

Elimina un carrito.

GET /api/v1/store/products/{productId}/validate

Valida si un producto existe, está activo y tiene stock disponible.
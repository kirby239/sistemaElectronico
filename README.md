# Aplicación de Full stack con Angular y Java

Este proyecto es una aplicación web de un examen de analista full-stack desarrollada con **Angular 18** en el frontend , **Java 18** en el backend , **Postgresql 16** y **Mongo DB ** con **Docker**. Incluye funcionalidades esenciales como  crear, leer, actualizar y eliminar pedidos, así como listar todos los
 pedidos y obtener detalles de un pedido específico. Un pedido tiene un cliente, una lista de
 productos y un total. 
---

## Funcionalidades

### Consideraciones
- **1. Las operaciones de creación y actualización de pedidos deben ser transaccionales y asegurar consistencia entre ambas bases de datos.**
- **2. Patrones de Diseño**
- **3. Implementar la aplicación de forma reactiva utilizando Spring WebFlux.**
- **4. Buenas Prácticas**
- **5. Manejar errores de forma adecuada.**
- **6. Validar entradas de usuario.**
- **7. Escribir tests unitarios para el código desarrollado.**
- **8. Docker**
- **Registro de Usuarios**: Permite a los usuarios crear una cuenta.
- **Inicio de Sesión**: Autentica a los usuarios mediante un sistema seguro basado en tokens.
- **Gestión de Tokens**: Generación y validación de JWT (JSON Web Token) para sesiones.
- **Recuperación de Contraseña**: Envío de correos electrónicos para restablecer la contraseña utilizando **Nodemailer**.
---

## Tecnologías Utilizadas

### Frontend
- **Angular 18**: Desarrollo de interfaces dinámicas y responsivas.
- **Angular Material** (opcional): Componentes predefinidos para la UI.
- **Bootstrap CSS** (opcional): Para personalizar estilos.

### Backend
- **JAVA 17**: Ejecución del servidor.
- **Spring boot**: Framework de java.
- **WebFlux,R2BC y Reactor**: reactivo .
- **PostgreSQL y MongoDB** : Persistencia de datos.

---

## API Endpoints

### Product
- **URL:** `GET http://localhost:8080/api/product/list`
- **URL:** `POST http://localhost:8080/api/product/save`

**Body:**
```json
{
    "name":"kirby",
    "description":"hecho el lima",
    "price":10,   
    "stock":20
}
```
### Customer
- **URL:** `GET http://localhost:8080/api/customer/list`
- **URL:** `POST http://localhost:8080/api/customer/list`

**Body:**
```json
{
    "name":"kirby",
    "email":"kirby@gmail.com
}
```

### Order/Pedido

- **URL:** `POST http://localhost:8080/api/orders/save`
- **URL:** `PUT http://localhost:8080/api/orders/{id}`
- **URL:** `GET http://localhost:8080/api/orders/list/order`
- **URL:** `GET http://localhost:8080/api/orders/customer/{customerid}`
- **URL:** `DELETE http://localhost:8080/api/orders/{id}`

**Body:**
```json
{
    "customerId": 1,
    "items": [
        {
            "productId": 1,
            "quantity": 4,
            "price": 40,
            "subtotal": 40
        }
    ],
    "total": 40,
    "status": "COMPLETED"
}
```

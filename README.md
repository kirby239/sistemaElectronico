# Aplicación de Autenticación con roles y CRUD con Angular y Node.js

Este proyecto es una aplicación web de un examen de analista full-stack desarrollada con **Angular 18** en el frontend , **Node.js 18** en el backend , **Postgresql 16** y **Postgresql 16** con **Docker**. Incluye funcionalidades esenciales como  crear, leer, actualizar y eliminar pedidos, así como listar todos los
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

### CRUD
- Operaciones de **Crear, Listar, Visualizar ,Actualizar y Eliminar** datos de los usuarios esto se aplica para el rol **SuperAdmin** y solo **Listar,Visualizar** para el rol de **User** .
- Gestión de perfiles de usuario a través de una API segura controlado los errores con sus repetivo estatus.

---

## Tecnologías Utilizadas

### Frontend
- **Angular 18**: Desarrollo de interfaces dinámicas y responsivas.
- **Angular Material** (opcional): Componentes predefinidos para la UI.
- **Bootstrap CSS** (opcional): Para personalizar estilos.

### Backend
- **Node.js 18**: Ejecución del servidor.
- **Express.js**: Framework para enrutamiento y middleware.
- **Nodemailer**: Servicio de correos electrónicos para recuperación de contraseñas.
- **JSON Web Token (JWT)**: Autenticación segura basada en tokens.
- **PostgreSQL** (o cualquier base de datos): Persistencia de datos.

---

## API Endpoints

### Login

**URL:** `POST http://localhost:3000/auth/login`

**Body:**
```json
{
    "username": "superadmin",
    "password": "superadmin"
}
```

### Register

**URL:** `POST http://localhost:3000/auth/register`

**Body:**
```json
{
    "username": "example",
    "password": "example"
    "email":"example@gmail.com",
    "name":"exampl"
}
```

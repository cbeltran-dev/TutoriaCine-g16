# 🎬 CineGO — Sistema de Venta de Entradas

---
## Contexto del negocio

**CineGO** es una cadena de cines que quiere digitalizar su operación. Hoy todo se maneja en hojas de cálculo: la cartelera se publica en una pizarra, las entradas se venden en boletería sin control real de aforo, y más de una vez se vendieron más entradas que asientos disponibles.

El dueño nos contrató para construir la **API REST** que será el corazón del nuevo sistema. El frontend lo hará otro equipo: nuestro contrato con ellos son los endpoints y sus respuestas JSON.

---

## Fase 1 — La API base

### RF-01 · Catálogo de películas
- Registrar películas con: **título**, **género** y **duración en minutos**.
- Listar todas las películas registradas.
- Buscar películas por género, **sin importar mayúsculas o minúsculas** ("comedia" y "Comedia" deben dar lo mismo).

### RF-02 · Programación de funciones
- Una función pertenece a **una película**; una película puede tener **muchas funciones**.
- Cada función tiene: **tipo** (`REGULAR` o `ESTRENO` — solo esos dos valores, el sistema debe rechazar cualquier otro), **fecha y hora**, **precio base** y **asientos disponibles**.
- No se pueden programar funciones en el pasado.
- Consultar la **cartelera**: funciones futuras ordenadas de la más próxima a la más lejana, mostrando el **título de la película** (no su id).
- Filtrar funciones por tipo.

### RF-03 · Venta de entradas
- Una entrada se compra para **una función**, indicando: nombre del cliente y cantidad de asientos.
- **Nunca se puede vender más asientos de los disponibles.** Si no alcanzan, la venta se rechaza por completo (no hay ventas parciales) y el stock de asientos queda intacto.
- Cada venta descuenta los asientos de la función **en la misma operación** — si algo falla a mitad de camino, no debe quedar ni la entrada creada ni los asientos descontados.
- La entrada guarda el **precio pagado al momento de la compra**: si el precio de la función cambia después, las entradas ya vendidas no se ven afectadas.

### Requerimientos técnicos (acordados con el equipo de frontend)

| Tema | Acuerdo |
|---|---|
| Arquitectura | Capas: controller → service → repository, con entidades JPA y PostgreSQL |
| Contrato de entrada/salida | La API **nunca** expone entidades: recibe y devuelve **DTOs** |
| Validaciones | Todo dato de entrada se valida (campos obligatorios, números positivos, fechas futuras, formato del tipo de función) |
| Respuestas exitosas | Envueltas en `ApiResponse`: `success`, `message`, `data`, `timestamp` |
| Respuestas de error | Formato estándar **ProblemDetail** (RFC 7807) |
| Códigos HTTP | `201` creación · `200` consulta · `400` datos inválidos (detallando **qué campos** fallaron) · `404` recurso inexistente · `409` conflicto con el estado del sistema (ej. asientos insuficientes) |
| Datos semilla | El proyecto debe arrancar con películas y funciones de prueba ya cargadas |

### Endpoints esperados

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/peliculas` | Registrar película |
| GET | `/api/v1/peliculas` | Listar películas |
| GET | `/api/v1/peliculas/genero/{genero}` | Buscar por género |
| POST | `/api/v1/funciones` | Programar función |
| GET | `/api/v1/funciones/cartelera` | Cartelera vigente |
| GET | `/api/v1/funciones/tipo/{tipo}` | Funciones por tipo |
| POST | `/api/v1/entradas` | Comprar entrada(s) |

### Criterios de aceptación (los probaremos en Postman)

1. La cartelera responde cada función con el título de su película.
2. Comprar 2 entradas para una función con 80 asientos deja 78 disponibles.
3. Comprar para una función inexistente responde `404`.
4. Comprar 999 entradas responde `409` con un mensaje claro de asientos insuficientes — y no descuenta nada.
5. Crear una función con body vacío responde `400` detallando cada campo inválido.

---

## Fase 2 — 🔒 Nuevo requerimiento del dueño

El sistema funciona y el dueño está contento... tanto, que quiere lanzar **promociones**:

- **Precio Estreno:** las funciones de tipo `ESTRENO` se cobran con un **recargo del 40%** sobre el precio base.
- **Promo Martes:** los días **martes**, las entradas valen la **mitad** del precio base.
- Si una función califica para más de una regla, se aplica **solo una**, según una prioridad definida por el negocio (el estreno manda sobre el martes).
- Toda entrada vendida debe registrar **qué promoción se le aplicó**, y esa información debe verse en la respuesta JSON de la compra.

Y la condición más importante, palabras textuales del dueño:

> *"Esto recién empieza: el próximo mes quiero más promociones. No puede ser que cada promo nueva implique reescribir y volver a probar el sistema de ventas entero."*

Es decir: el diseño debe permitir **agregar nuevas reglas de precio sin modificar el código existente** del flujo de compra.

*(Durante la sesión, el dueño anunciará una promoción sorpresa para poner a prueba ese diseño...)*

---

## Estructura del repositorio

El proyecto base ya tiene la estructura de paquetes y las dependencias configuradas (`Spring Web`, `Spring Data JPA`, `Validation`, `Lombok`, `PostgreSQL Driver`, `ModelMapper`). El código lo escribiremos en vivo durante la tutoría.

```
com.codigo.cine
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── service
└── strategy        ← ¿para qué será? 🤔
```

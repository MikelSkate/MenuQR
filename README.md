# 🍽️ Menu QR — Sistema de Pedidos desde Mesa

Aplicación web full stack para la gestión de pedidos en restaurantes mediante códigos QR. Desarrollada con **Spring Boot** en el backend y **HTML + CSS + JavaScript** en el frontend.

---

## 📋 Descripción

Menu QR permite a los clientes de un restaurante escanear un código QR desde su mesa y realizar pedidos directamente desde su móvil, sin necesidad de descargar ninguna aplicación.

Los pedidos se enrutan automáticamente a **cocina** o **barra** según el tipo de artículo, y el personal puede gestionar el estado de cada comanda en tiempo real desde su pantalla dedicada.

El dueño dispone de un panel de administración para gestionar el menú, las categorías, los artículos y las mesas del restaurante.

---

## ✨ Funcionalidades principales

- 📱 **Menú digital** accesible desde QR, sin instalación
- 🍳 **Pantalla de cocina** con pedidos de comida en tiempo real
- 🍺 **Pantalla de barra** con pedidos de bebida en tiempo real
- 👨‍💼 **Panel de administración** para gestionar menú y mesas
- ⚠️ **Gestión de alérgenos** por artículo 
- 🏷️ **Categorías** para organizar el menú (Entrantes, Principales, Postres, Bebidas...)
- 📊 **Estado de pedidos** (Pendiente →  Entregado)

---

## 🗂️ Modelo de datos

El proyecto cuenta con **6 entidades** principales:

| Entidad | Descripción |
|---------|-------------|
| `Mesa` | Mesas del restaurante, cada una con su QR único |
| `Pedido` | Comanda realizada desde una mesa |
| `LineaPedido` | Cada artículo dentro de un pedido con su cantidad y precio |
| `Articulo` | Platos y bebidas del menú |
| `Categoria` | Agrupación de artículos (Entrantes, Principal, Postres , Bebidas...) |
| `Alergeno` | Alérgenos asociados a los artículos |

### Relaciones

- `Mesa` 1:N `Pedido`
- `Pedido` 1:N `LineaPedido`
- `Articulo` 1:N `LineaPedido`
- `Categoria` 1:N `Articulo`
- `Articulo` N:M `Alergeno`
  
Modelo entidad relacion
<image src="media/modelo_entidad_relacion.jpeg" alt="Modelo relacional">
Diagrama UML
<image src="media/diagrama_UML.png" alt="Modelo relacional">

---

## 🛠️ Tecnologías utilizadas

### Backend
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Base de datos H2 (en memoria)
- Maven
- JUnit 5

### Frontend
- HTML5
- CSS3
- JavaScript (vanilla)

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos previos
- Java 17 o superior
- Maven 3.8 o superior

### Pasos

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/restaurantqr.git
cd restaurantqr
```

2. Compila y ejecuta el backend:
```bash
mvn spring-boot:run
```

3. Abre el frontend en el navegador:
```
http://localhost:8080/usuario/index.html?mesa=1
```
o escaneando un qr

<image src="media/qr.jpeg" alt="QR" width="200">

Para entrar al panel de administrador utilize
```
http://localhost:8080/admin/index.html
```

1. Accede a la consola H2 para ver la base de datos:
```
http://localhost:8080/h2-console

JDBC URL:  jdbc:h2:mem:restaurante
Usuario:   sa
Contraseña: (vacía)
```

---

## 📡 Rutas principales de la API

### Artículos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/articulos` | Lista todos los artículos |
| GET | `/api/v1/articulos/{id}` | Detalle de un artículo |
| POST | `/api/v1/articulos` | Crear artículo |
| PUT | `/api/v1/articulos/{id}` | Actualizar artículo |
| DELETE | `/api/v1/articulos/{id}` | Eliminar artículo |

### Pedidos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/pedidos` | Lista todos los pedidos |
| GET | `/api/v1/pedidos/{id}` | Detalle de un pedido |
| POST | `/api/v1/pedidos` | Crear pedido |
| PUT | `/api/v1/pedidos/{id}` | Actualizar pedido |
| PATCH | `/api/v1/pedidos/{id}/estado` | Cambiar estado del pedido |

### Mesas
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/mesas` | Lista todas las mesas |
| GET | `/api/v1/mesas/{id}` | Detalle de una mesa |
| POST | `/api/v1/mesas` | Crear mesa |
| PUT | `/api/v1/mesas/{id}` | Actualizar mesa |
| DELETE | `/api/v1/mesas/{id}` | Eliminar mesa |

### Categorías
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/categorias` | Lista todas las categorías |
| POST | `/api/v1/categorias` | Crear categoría |
| PUT | `/api/v1/categorias/{id}` | Actualizar categoría |
| DELETE | `/api/v1/categorias/{id}` | Eliminar categoría |

### Alérgenos
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/alergenos` | Lista todos los alérgenos |
| POST | `/api/v1/alergenos` | Crear alérgeno |
| PUT | `/api/v1/alergenos/{id}` | Actualizar alérgeno |
| DELETE | `/api/v1/alergenos/{id}` | Eliminar alérgeno |

---

## 📁 Estructura del proyecto

```
menuqr/
├── src/
│   ├── main/
│   │   ├── java/com/menuqr/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   └── DataLoader.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/restaurantqr/
│           └── service/
├── frontend/
│   ├── index.html
│   ├── css/
│   ├── js/
│   │   ├── services/
│   │   ├── views/
│   │   └── components/
├── docs/
│   └── (JavaDoc generado)
├── pom.xml
└── README.md
```

# Video resumiendo el proyecto 

[Vídeo de defensa](https://youtu.be/EUSJQI3XBOo)


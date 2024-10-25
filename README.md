# Proyecto: API de Productos

Este proyecto es una API RESTful desarrollada en **Jakarta EE** utilizando **Java**, desplegada en **WildFly** y gestionada con **Docker**. El propósito de esta aplicación es ofrecer servicios para gestionar productos y sus respectivas operaciones CRUD.

## Tabla de Contenidos

1. [Arquitectura de la Aplicación](#arquitectura-de-la-aplicación)
2. [Tecnologías Utilizadas](#tecnologías-utilizadas)
3. [Instrucciones de Instalación](#instrucciones-de-instalación)
4. [Instrucciones para Ejecutar la Aplicación](#instrucciones-para-ejecutar-la-aplicación)
5. [Despliegue en Producción](#despliegue-en-producción)
6. [Notas](#notas)

---

## Arquitectura de la Aplicación

La estructura del proyecto sigue un modelo modular y está organizada en las siguientes carpetas:

- **`apiproductos`**: Módulo principal de la API REST.
- **`src/main`**:
  - **`java/com/mycompany`**: Contiene el código fuente de la aplicación.
    - **`beans`**: Clases de negocio que manejan la lógica principal.
    - **`modelos`**: Entidades del sistema (objetos del dominio).
    - **`repositorios`**: Gestión de la persistencia de datos.
    - **`servicios`**: Lógica de negocio. Incluye el archivo `JakartaRestConfiguration.java`, que configura la API REST.
  - **`resources`**: Archivos de configuración y otros recursos necesarios para el funcionamiento de la aplicación.
  - **`webapp`**: Recursos de la interfaz web, como HTML, CSS y JavaScript.
- **`test`**: Clases para pruebas unitarias y de integración.
  
## Tecnologías Utilizadas

- **Java 11+**
- **Jakarta EE**
- **WildFly**
- **Docker**
- **Maven** para la gestión de dependencias
- **MySQL** como base de datos relacional

## Instrucciones de Instalación

### 1. Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalados los siguientes componentes:

- **JDK 11** o superior
- **Maven** (para gestionar las dependencias)
- **Docker** y **Docker Compose**

### 2. Clonar el Repositorio

```bash
git clone https://github.com/usuario/proyecto-apiproductos.git
cd proyecto-apiproductos

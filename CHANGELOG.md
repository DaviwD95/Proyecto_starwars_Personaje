# Changelog

Todos los cambios importantes de este proyecto se documentan aquí.  
La versión actual corresponde a la **Tarea 3**, implementando MVVM, Jetpack Compose y otras mejoras sobre la Tarea 2.

---
## [1.3.0] - Tarea 4 (2025-2-7)
### Added
- Implementacion de Previews especiales para pantallas 
- Implementacion de diseños comunes para la interfaz
- Implementacion de room para poder utilizar una base de datos y mantener los datos a pesar de reincios
- Implementacion de los Dao y Entidades
- Implementacion de un Drawer para mejor navegacion 
- Implementacion de film y planeta (estan comentadas las lineas de esto)
- Implementacion de control de permisos 
- Implementacion de comprobacion de nombre existente al crear
- Implementacion de notificaciones al crear un personaje (si se rechaza el permiso no cse crea)

### Fixed
- Arregle errores de validacion de campos relacionado a sus creaciones y ediciones

## [1.3.0] - Tarea 3 (2025-12-19)
### Added
- Implementación completa de la arquitectura **MVVM** con estados y ViewModels para añadir, editar y listar entidades.
- Creación de **repositorio estático** con simulación de datos usando `Flow` / `StateFlow`.
- Navegación entre pantallas mediante **Navigation Compose**.
- Animaciones de transición entre pantallas con **Accompanist Navigation-Animation**.
- Pantallas estructuradas con **Scaffold**, **Material3 Components**, y **FloatingActionButton** compartido con la pantalla de listado.
- **AlertDialog de confirmación** al eliminar un elemento del listado.
- **SnackBar sincronizado** con Scaffold al eliminar un elemento.
- Implementación de **pantallas de añadir, editar y listar entidad**, con **Card** para cada elemento de la lista.
- Elevación de estado (**state hoisting**) en todas las pantallas.
- Reutilización de componentes con propiedades similares.
- Creación de un **CompositionLocal personalizado** para modificar aspectos globales de la UI.
- Integración de **Hilt** para inyección de dependencias.
- Implementación de **@Preview avanzados** en todas las pantallas.
- Diseño completo de la **AboutScreen**, con información del desarrollador y estilo visual cuidado.

### Fixed
- Inclusión de iconos de acción en la barra superior (añadir y listar) según la Tarea 2.
- Menú overflow con opción “Sobre Nosotros” funcionando correctamente.
- Corrección de problemas de visualización en listas y cards.
- Sincronización correcta del FAB con la pantalla de listado y navegación a la ventana de añadir entidad.

---

## [1.2.0] - Tarea 2 
### Added
- Actividad principal con barra superior, iconos de añadir y listar.
- Menú overflow con opción “Sobre Nosotros”.
- Ventana para añadir la entidad correspondiente.
- Listado de elementos con iconos identificativos de cada categoría.
- Implementación inicial de state hoisting y reutilización de componentes.
- Uso del componente Card para mostrar elementos en el listado.

---

## [1.0.0] - Tarea 1 
### Added
- Creación del proyecto base en Android Studio.
- Configuración inicial de Jetpack Compose y dependencias.
- Primeros modelos de entidad (Personaje)

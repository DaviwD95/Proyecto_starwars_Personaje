# StarWars Personajes

App Android para gestionar personajes de Star Wars.  
Permite **listar, crear, editar y eliminar personajes** usando Jetpack Compose.

---

## Tecnologías

- Kotlin
- Jetpack Compose
- MVVM
- Hilt para inyección de dependencias
- Room para persistencia de datos
- Coroutines + Flow

---

## Arquitectura

- **MVVM**: separación de responsabilidades entre UI, ViewModel y repositorio.
- **State hoisting** para manejar el estado de la UI.
- **UI declarativa** con Jetpack Compose.
- Eventos de UI (clics, long click) controlados desde la Screen y confirmaciones via AlertDialog.

---

## Funcionalidades

- 📄 Listado de personajes
- ➕ Crear personaje
- ✏️ Editar personaje
- 🗑️ Eliminar personaje con **long click** y confirmación via `AlertDialog`
- Imagen por género del personaje
- Mensajes de “No hay datos” si la lista está vacía
- Manejo de estados de carga y error

---

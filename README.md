# StarWars Personajes

App Android para gestionar personajes de Star Wars.  
Permite **listar, crear, editar y eliminar personajes** usando Jetpack Compose, 
con sus respectivas validaciones y restricciones.

---

## Tecnologías

- Kotlin
- Jetpack Compose
- MVVM
- Hilt para inyección de dependencias
- Room para persistencia de datos
- Coroutines + Flow
- Base de datos
- Gestion de Permisos y notificaciones

---

## Arquitectura

- **MVVM**: separación de responsabilidades entre UI, ViewModel y repositorio.
- **State hoisting** para manejar el estado de la UI.
- **UI declarativa** con Jetpack Compose.
- Eventos de UI (clics, long click) controlados desde la Screen y confirmaciones via AlertDialog.
- Uso de Room con base de datos (Dao, Modulo Entidades) para almacenar los datos y con su persistencia
---

## Funcionalidades

- 📄 Listado de personajes
- ➕ Crear personaje
- ✏️ Editar personaje
- 🗑️ Eliminar personaje con **long click** y confirmación via `AlertDialog`
- Imagen por género del personaje
- Mensajes de “No hay datos” si la lista está vacía
- Manejo de estados de carga y error
- Gestion de permisos y notificaciones
- Drawer para mejor navegacion 
- Almacenamiento de datos usando una base de datos


## Dependencias

- Jetpack Compose
UI declarativa para la interfaz de usuario.

- Material 3
Componentes de diseño siguiendo las guías de Material Design.

- Navigation Compose
Navegación entre pantallas usando NavHost.

- ViewModel + Lifecycle
Gestión del estado y ciclo de vida de la aplicación.

- Room
Base de datos local para persistencia de personajes.

- Hilt
Inyección de dependencias.

- Kotlin Coroutines + Flow
Programación asíncrona y manejo reactivo de datos.

- Lottie
Animaciones para mejorar la experiencia de usuario.

- ConstraintLayout Compose
Layout avanzado para composiciones complejas.

- Accompanist Navigation Animation
Animaciones entre pantallas.

## Dependencia Tecnicas

// Compose
androidx.compose.ui
androidx.compose.material3
androidx.compose.foundation

// Lifecycle
androidx.lifecycle.runtime.ktx

// Navigation
androidx.navigation.compose

// Room
androidx.room.ktx
androidx.room.compiler

// Hilt
com.google.dagger:hilt-android
androidx.hilt.navigation.compose

// Animaciones
com.airbnb.android:lottie-compose
accompanist-navigation-animation


---

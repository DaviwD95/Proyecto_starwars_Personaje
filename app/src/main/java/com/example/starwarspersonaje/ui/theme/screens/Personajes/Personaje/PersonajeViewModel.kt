package com.example.starwarspersonaje.ui.theme.screens.Personajes.Personaje

import android.accounts.Account
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nagivigationviewmodel.data.network.BaseResult
import com.example.starwarspersonaje.ui.data.models.Errors.GeneroError

import com.example.starwarspersonaje.ui.data.models.Errors.NombreError
import com.example.starwarspersonaje.ui.data.models.Errors.NombreError.CaracteresInvalidos.validarNombre
import com.example.starwarspersonaje.ui.data.models.Errors.PlanetaError
import com.example.starwarspersonaje.ui.data.models.Personaje
import com.example.starwarspersonaje.ui.data.models.Planet
import com.example.starwarspersonaje.ui.repository.PersonajeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String


@HiltViewModel
class PersonajeViewModel @Inject constructor(

    private val personajeRepository: PersonajeRepository
): ViewModel() {


    val planetasPredefinidos = listOf(
        Planet(id = 1, name = "Tatooine"),
        Planet(id = 2, name = "Alderaan"),
        Planet(id = 3, name = "Naboo"),
        Planet(id = 4, name = "Coruscant"),
        Planet(id = 5, name = "Dagobah")
    )



    var state by mutableStateOf(PersonajeState())
        private set


    fun onChangeNombre(nombre : String)
    {
        val error = validarNombre(nombre)
        state = state.copy(nombre = nombre, nombreError = error )
    }







    //fun onChangePlanetaDeOrigen(planeta : String)
    //{
      //  state = state.copy(planetaDeOrigen = planeta)
   // }





    fun onChangePlaneta(planet: Planet) {


        state = state.copy(
            planetaId = planet.id,
            planetaDeOrigen = planet.name
        )
    }


    fun onChangeGenero(genero : String)
    {
        state = state.copy(genero = genero)
    }

    fun onChangeFechaNac(fecha : String)
    {
        state = state.copy(fechanac = fecha)
    }

    fun onChangeColorDeOjos(color : String)
    {
        state = state.copy(colorDeOjos = color)
    }

    fun onChangeIsInmortal(inmortal : Boolean)
    {
        state = state.copy(IsInmortal = inmortal)
    }



    //fun createdPersonaje() : Personaje
    //{
      //  return Personaje(
        //    nombre = state.nombre,
          //  planetaDeOrigen = state.planetaDeOrigen,
           // planetaId = state.planetaId!!,
            //id = state.id,
            //genero = state.genero,
            //fechanac = state.fechanac,
            //colorDeOjos = state.colorDeOjos,
            //IsInmortal = state.IsInmortal,
            //url ???? = ""
    //




    //
    // 
    // }

    fun createdPersonaje(): Personaje {
        return if (state.id == 0) {
            //Si es 0 pues creo uno nulo y luego room lo re asigna el solito
            Personaje(
                nombre = state.nombre,
                planetaId = state.planetaId!! , //?: error("Planeta no seleccionado")
                genero = state.genero,
                fechanac = state.fechanac,
                colorDeOjos = state.colorDeOjos,
                IsInmortal = state.IsInmortal,
            )
        } else {
            //Si no pues lo mantine ya pe
            Personaje(
                id = state.id,
                nombre = state.nombre,
                planetaId = state.planetaId ?: error("Planeta no seleccionado"),
                genero = state.genero,
                fechanac = state.fechanac,
                colorDeOjos = state.colorDeOjos,
                IsInmortal = state.IsInmortal,
            )
        }
    }




    fun addPersonaje()


    {

        if (!validarFormulario()) return

        val personaje = createdPersonaje()

        viewModelScope.launch {
            val result = personajeRepository.addPersonaje(personaje) //le cambie antes add

            if(result is BaseResult.Succes)
            {
                state = state.copy(isSaved = true)
            }
        }


    }

    fun updatePersonaje()
    {
        if (!validarFormulario()) return

        viewModelScope.launch {

            val result = personajeRepository.update(createdPersonaje())

            if(result is BaseResult.Succes)
            {
                state = state.copy(isSaved = true)
            }
        }


    }

    fun loadPersonaje(personaje: Personaje)
    {

        val planet = planetasPredefinidos.find { it.id == personaje.planetaId }


        state = state.copy(

            nombre = personaje.nombre,
            planetaDeOrigen = planet?.name ?: "",
            planetaId = personaje.planetaId,
            id = personaje.id,
            genero = personaje.genero,
            fechanac = personaje.fechanac,
            colorDeOjos = personaje.colorDeOjos,
            IsInmortal =  personaje.IsInmortal


        )
    }


    private fun validarFormulario(): Boolean {

        val nombreError = validarNombre(state.nombre)


        val planetaError = if (state.planetaId == null) PlanetaError.Vacio else PlanetaError.Okay



        val generoError = if (state.genero.isEmpty()) GeneroError.Vacio else GeneroError.Okay


        //realmente meterla en un estado de poco me sirve no ? osea puedo controlarlo bien si eso

        state = state.copy(
            nombreError = nombreError,
            planetaError = planetaError,
            generoError = generoError
        )
        var condicion : Boolean

        if (!nombreError.isError() && !planetaError.isError() && !generoError.isError()
        ) {
            state = state.copy(mensajeError = "")
            condicion = true
        } else {
            state = state.copy(mensajeError = "Error, tienes que rellenar nombre, planeta y genero")
           condicion =  false
        }

        return  condicion

    }

}
package com.example.starwarspersonaje.ui.data.models.Errors

sealed class PersonajeExecepction(message : String) : Exception(message)

{



    data class Exists (val mensaje : String) : PersonajeExecepction("Error, el personaje ya existe")

    data class NameExists(val mensaje : String) : PersonajeExecepction("Error, ya hay un personaje con ese nombre")

    data object NotFound : PersonajeExecepction("Error, no se encontro el personaj")

    data object None : PersonajeExecepction("")


    open fun isError() : Boolean
    {
        return this != None
    }


}
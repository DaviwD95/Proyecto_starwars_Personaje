package com.example.starwarspersonaje.ui.data.models.Errors

sealed class PersonajeExecepction(message : String) : Exception(message)

{



    data class Exists (val mensaje : String) : PersonajeExecepction("Error, la cuneta ya existe")


    data object NotFound : PersonajeExecepction("Error, no se encontro la cuenta")

    data object None : PersonajeExecepction("")


    open fun isError() : Boolean
    {
        return this != None
    }


}
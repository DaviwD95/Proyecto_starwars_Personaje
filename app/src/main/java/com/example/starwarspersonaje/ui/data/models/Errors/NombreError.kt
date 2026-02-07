package com.example.starwarspersonaje.ui.data.models.Errors

sealed class NombreError ( message : String) : Exception(message){




    data object Ninguno : NombreError("No ha indicado ningun nombre")
    data object CaracteresInvalidos : NombreError("Los caracteres son invalidos")
    data object MuyCorto : NombreError("Es muy corto")


    fun validarNombre(nombre: String): NombreError {
        val regex = Regex("^[a-zA-Z0-9-]*$")

        return when {
            !regex.matches(nombre) -> CaracteresInvalidos
            nombre.length < 3 -> MuyCorto
            else -> Ninguno
        }
    }


    fun isError() = this != Ninguno





}
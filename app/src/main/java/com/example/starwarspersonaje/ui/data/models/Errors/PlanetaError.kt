package com.example.starwarspersonaje.ui.data.models.Errors

sealed class PlanetaError (message : String) : Exception(message){




    data object Vacio : PlanetaError("No puede estar vacio")

    data object Okay : PlanetaError("")


    fun isError() = this != Okay





}
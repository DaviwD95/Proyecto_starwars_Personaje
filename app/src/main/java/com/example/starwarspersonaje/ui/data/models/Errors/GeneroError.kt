package com.example.starwarspersonaje.ui.data.models.Errors

sealed class GeneroError (message : String) : Exception(message){




    data object Vacio : GeneroError("No puede estar vacio")

    data object Okay : GeneroError("")


    fun isError() = this != Okay





}
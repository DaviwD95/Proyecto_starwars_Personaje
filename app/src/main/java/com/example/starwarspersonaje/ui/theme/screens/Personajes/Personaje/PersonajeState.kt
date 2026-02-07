package com.example.starwarspersonaje.ui.theme.screens.Personajes.Personaje

import com.example.starwarspersonaje.ui.data.models.Errors.GeneroError
import com.example.starwarspersonaje.ui.data.models.Errors.NombreError
import com.example.starwarspersonaje.ui.data.models.Errors.PlanetaError


data class PersonajeState (



    var nombre : String = "",

    var nombreError: NombreError = NombreError.Ninguno,




    var planetaDeOrigen : String = "",

    var planetaError : PlanetaError = PlanetaError.Okay,

    //deberia tener planetaErroo



    var planetaId: Int? = null,  // FK

    var id : Int = 0, //Detalle, es auto increment de normal no se ?

    var genero : String = "",

    var generoError : GeneroError = GeneroError.Okay,
    //deberia tener generoErro



    var fechanac : String = "",

    var colorDeOjos : String = "",

    var IsInmortal : Boolean = false,

    var isSaved : Boolean = false,

    var mensajeError : String = ""



    )
package com.example.starwarspersonaje.ui.theme.screens.Personajes.List

import com.example.starwarspersonaje.ui.data.models.Personaje

sealed class PersonajeListState {


    data object NoData : PersonajeListState()

    data object Loading : PersonajeListState()

    data class Succes(var lista : List<Personaje>) : PersonajeListState()

    data class Error(var mensaje : String) : PersonajeListState()




}
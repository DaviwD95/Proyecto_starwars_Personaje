package com.example.starwarspersonaje.ui.data.models



import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation




data class FilmWithPlanets(

    @Embedded
    val film: Film,

    @Relation(
        parentColumn = "filmId",       // columna en Film
        entityColumn = "id",           // columna en Planet que recibirá la relación
        associateBy = Junction(
            value = FilmPlanetEntity::class,
            parentColumn = "filmId",   // columna en FilmPlanetEntity que apunta a Film
            entityColumn = "planetId"  // columna en FilmPlanetEntity que apunta a Planet
        )
    )
    val planets: List<Planet>
)

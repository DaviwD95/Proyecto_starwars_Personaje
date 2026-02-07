package com.example.starwarspersonaje.ui.data.models

//PERSONAJE WITH PLANET

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "personaje_planet",
    primaryKeys = ["personajeId", "planetId"],
    foreignKeys = [
        ForeignKey(
            entity = Personaje::class,
            parentColumns = ["id"],
            childColumns = ["personajeId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Planet::class,
            parentColumns = ["id"],
            childColumns = ["planetId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("personajeId"),
        Index("planetId")
    ]
)
data class PersonajePlanet(
    val personajeId: Int,
    val planetId: Int
)

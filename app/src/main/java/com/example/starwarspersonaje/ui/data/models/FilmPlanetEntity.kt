package com.example.starwarspersonaje.ui.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity




import androidx.room.ForeignKey
import androidx.room.Index


//CUIDA, ME AYUDO CHAT NO SE SI ESTA BIEN (CREO QUE SI )
@Entity(
    tableName = "film_planet",
    primaryKeys = ["filmId", "planetId"],
    foreignKeys = [
        ForeignKey(
            entity = Film::class,
            parentColumns = ["filmId"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Planet::class,
            parentColumns = ["id"],
            childColumns = ["planetId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],

    //NO ESTOY SEGURO SI LO TIENE
    indices = [
        Index("filmId"),
        Index("planetId")
    ]
)

data class FilmPlanetEntity(
    @ColumnInfo(name = "filmId")
    val filmId: String,
    @ColumnInfo(name = "planetId")
    val planetId: Int
)

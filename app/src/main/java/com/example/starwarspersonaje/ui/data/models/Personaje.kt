package com.example.starwarspersonaje.ui.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize



//TENGO QU ADAPTAR ESTO A MI DIAGRAMA


@Entity(
    tableName = "personaje",
    /**
     *
     * foreignKeys = [
     *         ForeignKey(
     *             entity = Planet::class,           // Tabla destino
     *             parentColumns = ["id"],           // Columna de Planet
     *             childColumns = ["planetaId"],    // Columna en Personaje
     *             onDelete = ForeignKey.RESTRICT  // Qué pasa si el planeta se borra
     *         )
     *     ]
     */

)
@Parcelize
data class Personaje (

    @PrimaryKey (autoGenerate = true)
    var id : Int = 0,
    //@NonNull


    var nombre : String,
    //var planetaDeOrigen : String,
    var planetaId : Int,

    var genero : String,
    var fechanac : String,
    var colorDeOjos : String,
    var IsInmortal : Boolean

) : Parcelable



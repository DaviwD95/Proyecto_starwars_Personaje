package com.example.specie.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import com.example.starwarspersonaje.ui.data.models.Personaje
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonajeDao {
    @Insert
    suspend fun insert(personaje: Personaje) //añadi suspend a all

    @Delete
    suspend fun delete(personaje: Personaje)

    @Update
    suspend fun update(personaje: Personaje)

    @Query("SELECT * FROM personaje")
    fun getAll () : Flow<List<Personaje>>//Lo tengo que hacer con un flujo pepe, pendiente cambiar

    @Query("SELECT EXISTS (SELECT * FROM personaje WHERE personaje.id = :id )")
    suspend fun exists(id : Int): Boolean

    @Query("SELECT * FROM personaje WHERE id = :id")
    suspend fun getByID(id : Int) : Personaje?
}
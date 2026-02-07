package com.example.starwarspersonaje.ui.data.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.starwarspersonaje.ui.data.models.Film
import com.example.starwarspersonaje.ui.data.models.FilmWithPlanets
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) para la entidad Film
 * proporciona metodos para insertar, actualizar, eliminar y consultar peliiculas en la base de datos
 */
@Dao
interface FilmDAO {

    /**
     * inserta una pelicul en la base de datos
     * si la pelicula ya existe, lanzará un error
     */
    @Insert
    fun insert(film: Film)

    /**
     * actualiza una pelicula exstente en la base de datos
     * coincide por filmId y reemplaza los valores
     */
    @Update
    fun update(film: Film)

    /**
     * Elimina una pelicula de la base de datos
     */
    @Delete
    fun delete(film: Film)

    /**
     * devuelve todas las peliculas almacenadas en la base de datos como un Flow
     * cada cambio en la tabla 'film' emitira automáticamente la lista actualizada
     */
    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<Film>>

    /**
     * comprueba si existe una pelicula con un filmId especific
     * @param id: Identificador de la pelicula
     * @return true si existe, false si no existe
     */
    @Query("SELECT EXISTS(SELECT 1 FROM film WHERE film.filmId = :id)")
    fun exists(id: String): Boolean

    /**
     * devuelve todas las pelculas ordenadas por titulo en orden des
     */
    @Query("SELECT * FROM film ORDER BY title DESC")
    fun orderByNameDesc(): Flow<List<Film>>

    /**
     * comprueba si ya existe una pelicula con un mismo titulo y fecha de estreno
     * @param title: título de la pelicula a comproba
     * @param releaseDate: fecha de estreno a comprobar
     * @return true si ya existe, false si no
     */
    @Query("SELECT EXISTS(SELECT 1 FROM film WHERE LOWER(title) = LOWER(:title) AND releaseDate = :releaseDate)")
    fun existsByTitleAndDate(title: String, releaseDate: String): Boolean


    @Transaction
    @Query ("SELECT * FROM film WHERE filmId = :id")
    fun getFilmWithPlanet(id: Int) : FilmWithPlanets

}
package com.example.specie.data.dao

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.starwarspersonaje.ui.data.dao.FilmDAO
import com.example.starwarspersonaje.ui.data.dao.PlanetDao
import com.example.starwarspersonaje.ui.data.models.Film
import com.example.starwarspersonaje.ui.data.models.FilmPlanetEntity
import com.example.starwarspersonaje.ui.data.models.FilmWithPlanets

import com.example.starwarspersonaje.ui.data.models.Personaje
import com.example.starwarspersonaje.ui.data.models.PersonajePlanet
import com.example.starwarspersonaje.ui.data.models.Planet
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    version = 11,
    entities = [
        Personaje::class,
        //Film :: class,
        //Planet :: class,
        //PersonajePlanet:: class,
        //FilmPlanetEntity :: class,
               ],
    exportSchema = false
)
abstract class StartWarsDatabase : RoomDatabase() {
    abstract fun getPersonajeDao(): PersonajeDao
   // abstract fun getPlanetDao(): PlanetDao
    //abstract fun getFilmDao(): FilmDAO

    //En las anotaciones en Kotlin los arrays es con corchetes

    companion object {
        /**
         * La variable se guarda en memoria. Cualquier cambio realizado en la variable por un hilo
         * se refleja de inmediado y es visible al resto de hilos. No hay copias antiguas o nulas.
         */
        @Volatile
        private var INSTANCE: StartWarsDatabase? = null

        //private var tempDb: StartWarsDatabase? = null

        fun getDatabase(context: Context): StartWarsDatabase {
            return INSTANCE ?: synchronized(this) {

                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    StartWarsDatabase::class.java,
                    "login_database.db"
                )
                    .fallbackToDestructiveMigration(true) //linea cuidao, que
                    // Callback para pre-poblar la base de datos
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Se utiliza un executor para realizar la inserción en un hilo de fondo
                            //Las tareas se ejecutan de forma secuencial en un hilo/s


                            Executors.newSingleThreadExecutor().execute {

                                println(" PREPOPULATE EJECUTADO ")




                                INSTANCE?.let { database ->
                                prepopulateDatabase(database)
                                }

                                //tempDb?.let {
                                  //  prepopulateDatabase(it)
                                //}

                            }
                        }
                    })
                    .build()
                //tempDb = dbInstance
                INSTANCE = dbInstance
                dbInstance
            }
        }
        fun prepopulateDatabase(database: StartWarsDatabase){
            val personajeDao = database.getPersonajeDao()
            //val planetaDao = database.getPlanetDao()
            //val filmDao = database.getFilmDao()


            //tengo que insertar planetas, ademas de un resultPersonWithPlante
            runBlocking {

                // PRIMERO planetas
                /**
                 * planetaDao.insert(
                 *                     Planet(
                 *                         id = 2,
                 *                         rotation_period = 40,
                 *                         orbital_period = 30,
                 *                         climate = "Arido",
                 *                         terrain = "Feo",
                 *                         discovery_date = "4/12/2700",
                 *                         is_colonized = true,
                 *                         user_id = 3
                 *                     )
                 *                 )
                 */

                // 2LUEGO personajes
                personajeDao.insert(
                    Personaje(
                        nombre = "Aelion",
                        planetaId = 2,
                        genero = "Male",
                        fechanac = "10-10-2000",
                        colorDeOjos = "Azul",
                        IsInmortal = false
                    )
                )
                personajeDao.insert(
                    Personaje(
                        nombre = "Pedrito",
                        planetaId = 1,
                        genero = "Female",
                        fechanac = "10-10-2000",
                        colorDeOjos = "Verde",
                        IsInmortal = false
                    )
                )

                // 3 luego films (no dependen)
                /**
                 * filmDao.insert(
                 *                     Film(
                 *                         filmId = "4",
                 *                         title = "The potaxie war",
                 *                         episode = "4",
                 *                         director = "Paolita Suarez",
                 *                         releaseDate = "20/4/3000",
                 *                         era = "Futura",
                 *                         rating = "10.0",
                 *                         openingText = "Bienvenidos!!!!!",
                 *                         isOriginalTrilogy = true,
                 *                     )
                 *                 )
                 */
            }
        }
    }
}
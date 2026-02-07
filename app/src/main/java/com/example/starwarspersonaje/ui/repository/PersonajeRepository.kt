package com.example.starwarspersonaje.ui.repository

import com.example.nagivigationviewmodel.data.network.BaseResult
import com.example.specie.data.dao.PersonajeDao
import com.example.starwarspersonaje.ui.data.dao.FilmDAO
import com.example.starwarspersonaje.ui.data.dao.PlanetDao
import com.example.starwarspersonaje.ui.data.models.Personaje
import com.example.starwarspersonaje.ui.data.models.Errors.PersonajeExecepction


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonajeRepository @Inject constructor(
    val personajesDataBase : PersonajeDao,
    //val filmsDataBase : FilmDAO,
   // val planetsDao: PlanetDao
) {


   // private var dataset : MutableList<Personaje> = mutableListOf()

   // var personajes =  MutableStateFlow<List<Personaje>>(emptyList())




    private var selectedPersonaje: Personaje? = null

    fun getData(): Flow<List<Personaje>> = personajesDataBase.getAll()

    suspend fun selectPersonaje(personaje : Personaje) : BaseResult<Personaje> {

        delay(2000)

        val found = personajesDataBase.getByID(personaje.id)

         if (found != null) {
            selectedPersonaje = found
           return  BaseResult.Succes(found)
        } else {
            return  BaseResult.Error(PersonajeExecepction.NotFound)
        }


    }

    fun getSelected(): Personaje? = selectedPersonaje



    //aca toco algo
    suspend fun addPersonaje(personaje: Personaje) : BaseResult<Personaje> {
        if (personaje.id > 0) {
            val existe = personajesDataBase.exists(personaje.id)
            if (existe) return BaseResult.Error(PersonajeExecepction.Exists("Personaje ya existe"))
        }

        personajesDataBase.insert(personaje)
        return BaseResult.Succes(personaje)
    }


    suspend fun deletePersonaje(personaje: Personaje) {
        personajesDataBase.delete(personaje)
    }

    suspend fun update(personaje: Personaje) : BaseResult<Personaje>
    {
        val oldPersonaje =  personajesDataBase.getByID(personaje.id)

        if(oldPersonaje != null)
        {
            personajesDataBase.delete(oldPersonaje)

            personajesDataBase.insert(personaje)


            return BaseResult.Succes(personaje)
        }
        return BaseResult.Error(PersonajeExecepction.NotFound)



    }


//Antiguos

    /**
     * fun update(personaje: Personaje) : BaseResult<Personaje>
     *     {
     *
     *         val oldPersonaje = dataset.firstOrNull(){personaje.id == it.id}
     *
     *         if(oldPersonaje != null)
     *         {
     *             dataset.remove(oldPersonaje)
     *             dataset.add(personaje)
     *             // notifyChange()
     *             return BaseResult.Succes(personaje)
     *         }
     *         return BaseResult.Error(PersonajeExecepction.NotFound)
     *
     *
     *
     *     }
     *
     *     fun add(personaje: Personaje) : BaseResult<Personaje>
     *     {
     *
     *
     *
     *         if(personaje != null && !dataset.contains(personaje))
     *         {
     *             val newId = (dataset.maxOfOrNull { it.id } ?: 0) + 1
     *
     *             val newPersonaje = personaje.copy(id = newId)
     *
     *             dataset.add(newPersonaje)
     *             // notifyChange()
     *
     *             return BaseResult.Succes(newPersonaje)
     *         }
     *
     *         return BaseResult.Error(PersonajeExecepction.Exists("Error, ya existe el personaje"))
     *
     *     }
     *
     *     fun delete(personaje: Personaje){
     *
     *         dataset.remove(personaje)
     *         //  notifyChange()
     *
     *     }
     */








    /**
     * init {
     *         inicializar()
     *         notifyChange()
     *     }
     */

    //////////

    /**
     *
     *
     *     fun notifyChange()
     *     {
     *         personajes.value = dataset.toList()
     *     }
     *
     *     private fun inicializar() {
     *         //MAsc
     *         dataset.add(
     *             Personaje(
     *                 nombre = "Darth Revan",
     *                 planetaId = 1, //cambie de String a int
     *                 id = 4,
     *                 genero = "Male",
     *                 fechanac = "3994 ABY",
     *                 colorDeOjos = "Marrones",
     *                 IsInmortal = false,
     *
     *             )
     *         )
     *
     *         // 🌕 Femenino
     *         dataset.add(
     *             Personaje(
     *                 nombre = "Ahsoka Tano",
     *                 planetaId = 2,
     *                 id = 5,
     *                 genero = "Female",
     *                 fechanac = "36 ABY",
     *                 colorDeOjos = "Azules",
     *                 IsInmortal = false,
     *
     *
     *             )
     *         )
     *
     *         // 🌗 Hermaphrodite
     *         dataset.add(
     *             Personaje(
     *                 nombre = "Jabba the Hutt",
     *
     *                 id = 6,
     *                 genero = "Hermaphrodite",
     *                 fechanac = "600 ABY",
     *                 colorDeOjos = "Amarillos",
     *                 IsInmortal = false,
     *                 planetaId = 4,
     *
     *             )
     *         )
     *     }
     *
     *
     *     //fun getData(): List<Personaje> = dataset
     *
     *
     *     fun getDataFlow(): Flow<List<Personaje>> = flow {
     *         emit(emptyList())     // Primero lista vacía
     *         delay(1000)           // Simulación de carga
     *         emit(dataset)         // Luego lista real
     *     }
     *
     *     // Devuelve el primer personaje
     *     fun getFirst(): Personaje = dataset.first()
     *
     *
     */








}
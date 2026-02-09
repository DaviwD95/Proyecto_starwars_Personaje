    package com.example.starwarspersonaje.ui.theme.screens.Personajes.List

    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.setValue
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.starwarspersonaje.ui.data.models.Personaje
    import com.example.starwarspersonaje.ui.repository.PersonajeRepository
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.flow.flowOn
    import kotlinx.coroutines.launch
    import javax.inject.Inject


    @HiltViewModel
    class PersonajeListViewModel @Inject constructor(

        private val personajeRepository: PersonajeRepository
    ) : ViewModel()
    {

        var state : PersonajeListState by mutableStateOf(PersonajeListState.Loading)
       private set


        //NADOTA DE _PERSONAJES PERSONAJES Y ESO, TIENE QUE SER CON COLLECT Y ESO, PUEDE SER COLLECTASSTATE ATENTO
        //CON COMENTARIOS Y ESO

    init {
        getData()
        //getDataProbando()

    }



        private fun getData() {

            state = PersonajeListState.Loading


            viewModelScope.launch {

                personajeRepository.getData().collect { personajes ->
                    state = if (personajes.isEmpty()) {
                        PersonajeListState.NoData
                    } else {
                        PersonajeListState.Succes(personajes)
                    }
                }
            }
        }

        private fun getDataProbando()
        {
            viewModelScope.launch {
                personajeRepository.getData()
                    .flowOn(Dispatchers.IO) // 👈 AQUÍ
                    .collect { personajes ->
                        state = if (personajes.isEmpty())
                            PersonajeListState.NoData
                        else
                            PersonajeListState.Succes(personajes)
                    }
            }

        }






        fun delete(personaje: Personaje) {
            // Lanzamos coroutine en ViewModelScope
            viewModelScope.launch {
                personajeRepository.deletePersonaje(personaje) // ⚡ delete es suspend
                // Si quieres, aquí actualizas la lista luego de eliminar

            }
        }

        fun ordenar()
        {

        }

        /**
         *
         * fun getData()
         *         {
         *
         *
         *             state = PersonajeListState.Loading
         *
         *             viewModelScope.launch {
         *
         *
         *                 //val personajs =  personajeRepository.getData()
         *
         *
         *                 personajeRepository.getData().collect { accounts -> //antes era collect
         *                     if(accounts.isEmpty())
         *                     {
         *                         state = PersonajeListState.NoData
         *                     }
         *                     else{
         *                         state = PersonajeListState.Succes(accounts)
         *                     }
         *                 }
         *             }
         *         }
         */









    }
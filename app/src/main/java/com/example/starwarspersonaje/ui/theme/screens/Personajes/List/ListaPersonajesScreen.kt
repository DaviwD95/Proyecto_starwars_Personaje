package com.example.starwarspersonaje.ui.theme.screens.Personajes.List

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarspersonaje.R
import com.example.starwarspersonaje.ui.base.screens.AlertDialogOkCancel
import com.example.starwarspersonaje.ui.base.screens.NoDataScreen
import com.example.starwarspersonaje.ui.common.AppSpacing
import com.example.starwarspersonaje.ui.common.CardStyle
import com.example.starwarspersonaje.ui.common.LocalCardStyle
import com.example.starwarspersonaje.ui.data.models.Personaje
import com.example.starwarspersonaje.ui.theme.StarwarsPersonajeTheme


data class PersonajeListEvents(

    val onEditPersonaje: (Personaje) -> Unit,
    val onAddPersonaje: () -> Unit,
    val onLongClick : (Personaje) -> Unit, //delete pues
    val onOrdenar : () -> Unit
    )



@Composable
fun PersonajeListScreen(modifier: Modifier, goToAdd : () -> Unit, goToEdit : (Personaje) -> Unit,
                        viewModel : PersonajeListViewModel)
{
    var showAlert by remember { mutableStateOf(false) }
    var personajeSeleccionado by remember { mutableStateOf<Personaje?>(null) }

    val eventos = PersonajeListEvents(

        onEditPersonaje = goToEdit,
        onAddPersonaje = goToAdd,
        onLongClick = {personaje ->
            personajeSeleccionado = personaje
            showAlert = true },
        onOrdenar = viewModel:: ordenar
    )

    var state = viewModel.state

    when(state)
    {
        is PersonajeListState.Error -> NoDataScreen()
        PersonajeListState.Loading -> NoDataScreen()
        PersonajeListState.NoData -> NoDataScreen()
        is PersonajeListState.Succes -> PersonajeListContent(modifier,eventos, state.lista )
    }

    if(showAlert && personajeSeleccionado!=null)
    {
        AlertDialogOkCancel(
            title = "Elminar Personaje",
            text = "Seguro que quieres eliminar el personaje? ",
            okText = "Si",
            cancelText = "No",
            onConfirm = {viewModel.delete(personajeSeleccionado!!)
                        showAlert = false
                        personajeSeleccionado = null},
            onDismiss = {showAlert = false
                        personajeSeleccionado = null},
            )
    }






}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonajeListContent(modifier: Modifier, events : PersonajeListEvents,lista : List<Personaje>)
{

    val lazyState = rememberLazyListState()


    Scaffold(



        floatingActionButton = {

            FloatingActionButton(

                onClick = { events.onAddPersonaje() }


            ) {
                Icon(Icons.Filled.Add, "Floating añadir Personje")
            }

        }
    ){ paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .padding(paddingValues),

            state =lazyState,
        verticalArrangement = Arrangement.spacedBy(AppSpacing.md)
    ) {

        items(lista) { personaje ->


            PersonajeItem(modifier = Modifier.combinedClickable
                (

                onClick = { events.onEditPersonaje(personaje) },
                //on longClick delete
                onLongClick = {events.onLongClick(personaje)}

            ), personaje= personaje)

        }

    }}

}

@Composable
fun PersonajeItem(modifier: Modifier = Modifier, personaje: Personaje)
{


    Card(

        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = LocalCardStyle.current.containerColor,
            contentColor = Color.Yellow //white quizas
        ),
        elevation = CardDefaults.cardElevation(LocalCardStyle.current.elevation),

        )
    {

        Row(

            modifier = Modifier.fillMaxWidth().padding(LocalCardStyle.current.padding)
        )
        {
            Image(

                contentDescription = "Imagen de cada card",
                modifier = Modifier,
                painter = painterResource(

                    when(personaje.genero)
                    {
                        "Male" -> R.drawable.masculino
                        "Female" -> R.drawable.star_wars_icono_femenino
                        "Hermaphrodite" -> R.drawable.hermafrodita
                        else -> R.drawable.account_icon

                    }

                )

            )


            Column(

                verticalArrangement = Arrangement.spacedBy(5.dp)
            )
            {


                Text(
                    text = "Nombre : ${personaje.nombre}",
                    style = MaterialTheme.typography.titleMedium
                )


                Text(
                    text = "Planeta de origen :  ${personaje.planetaId}",
                    style = MaterialTheme.typography.bodyMedium
                )


                Text(
                    text = " Genero :  ${personaje.genero}",
                    style = MaterialTheme.typography.bodyMedium
                )






            }




        }









    }



}

//Para daltonicos y Personas que quieren la letra mas grandecita
@Preview(
    showBackground = true,
    showSystemUi = true,
    fontScale = 1.5f,
    backgroundColor = 0xFF0D47A1

)
@Composable
fun PersonajeListScreenPreviewDaltonicoFuenteGrande()
{
    StarwarsPersonajeTheme(colorBlind = true) {


        // Mock de datos de ejemplo
        val personajesFake = listOf(
            Personaje(id = 1, nombre = "Luke Skywalker", planetaId = 1, genero = "Male", colorDeOjos = "Azul", fechanac = "20-210-2000", IsInmortal = false),
            Personaje(id = 2, nombre = "Leia Organa", planetaId = 2, genero = "Female", colorDeOjos = "Verde", fechanac = "20-210-2000", IsInmortal = false),
            Personaje(id = 3, nombre = "Hermaphrodita Jedi", planetaId = 3, genero = "Hermaphrodite", colorDeOjos = "Azul", fechanac = "20-210-2000", IsInmortal = true),
        )

        // Mock de eventos (no hacen nada en preview)
        val eventosFake = PersonajeListEvents(
            onEditPersonaje = {},
            onAddPersonaje = {},
            onLongClick = {},
            onOrdenar = {}
        )

        StarwarsPersonajeTheme(colorBlind = true) {
            PersonajeListContent(
                modifier = Modifier.fillMaxSize(),
                events = eventosFake,
                lista = personajesFake
            )
        }
    }
}






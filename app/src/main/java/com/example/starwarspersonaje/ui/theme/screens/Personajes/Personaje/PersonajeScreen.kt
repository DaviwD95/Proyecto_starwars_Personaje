package com.example.starwarspersonaje.ui.theme.screens.Personajes.Personaje

import android.provider.CalendarContract
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.starwarspersonaje.ui.data.models.Errors.NombreError
import com.example.starwarspersonaje.ui.data.models.Personaje
import com.example.starwarspersonaje.ui.data.models.Planet
import com.example.starwarspersonaje.ui.helper.AppPermissions
import com.example.starwarspersonaje.ui.helper.NotificationHandler
import com.example.starwarspersonaje.ui.helper.rememberPermissionsLauncher
import com.example.starwarspersonaje.ui.theme.StarwarsPersonajeTheme
import com.example.starwarspersonaje.ui.theme.componentes.ColorOjosDropdown
import com.example.starwarspersonaje.ui.theme.componentes.ConfirmarButton

import com.example.starwarspersonaje.ui.theme.componentes.FechaNacimientoField
import com.example.starwarspersonaje.ui.theme.componentes.GeneroDropdown
import com.example.starwarspersonaje.ui.theme.componentes.IdField
import com.example.starwarspersonaje.ui.theme.componentes.IsInmortal
import com.example.starwarspersonaje.ui.theme.componentes.NombreField
import com.example.starwarspersonaje.ui.theme.componentes.PlanetaField


data class PersonajeEvents
    (
    val onChangeNombre : (String) -> Unit,
    val onChangePlaneta : (Planet) -> Unit,
    val onChangeGenero : (String) -> Unit,
    val onChangeFechaNac : (String) -> Unit,
    val onChangeColorOjos : (String) -> Unit,
    val onChangeIsInmortal : (Boolean) -> Unit,
    val onConfirmar : () -> Unit,
)

@Composable
fun PersonajeCreacion(modifier: Modifier = Modifier, viewModel: PersonajeViewModel,
                      goToBack : () -> Unit)
{






    val state = viewModel.state


    //esto es lo que me pediran prpgramar creo

    val context = LocalContext.current
    val notificationHandler = remember{ NotificationHandler(context) }

    // Launcher genérico para permisos (aquí solo pedimos Notificaciones)
    val requestNotificationPermissionThenNotify = rememberPermissionsLauncher(

        //apartir de aca nosotros tenemos que rellenarlo
        permissions = listOf(AppPermissions.Notifications),
        onAllGranted = {

            //notificationHandler.showSimpleNotification(
             //   contentTitle = "Personaje creado",
               // contentText = "Se ha dado de alta ${state.nombre}" // ui state ????
            //)
            //goToBack()
            viewModel.addPersonaje()
        },
        onDenied = { denied ->

            Toast.makeText(
                context,
                "Necesitamos permiso para mostrar notificaciones al crear personajes",
                Toast.LENGTH_LONG
            ).show()

            // Opcional: si no concede, puedes avisar con snackbar
           // onShowSnackbar("Permiso de notificaciones denegado")

        }
    )

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) {

            notificationHandler.showSimpleNotification(
                contentTitle = "Elemento creado",
                contentText = "Personaje ${state.nombre} creado con exito"
            )

            goToBack()
        }
    }



    val eventos = PersonajeEvents(

        onChangeNombre = viewModel::onChangeNombre,
        onChangePlaneta  = viewModel :: onChangePlaneta,
        onChangeGenero = viewModel::onChangeGenero,
        onChangeFechaNac = viewModel::onChangeFechaNac,
        onChangeColorOjos = viewModel::onChangeColorDeOjos,
        onChangeIsInmortal = viewModel::onChangeIsInmortal,
        //onConfirmar = viewModel::addPersonaje,
        onConfirmar = {
            requestNotificationPermissionThenNotify()
        }
    )




    PersonajeContent(modifier, state, eventos,"Creacion")
}


@Composable
fun PersonajeEdicion(modifier: Modifier = Modifier, viewModel: PersonajeViewModel,
                     personaje: Personaje, goToBack : () -> Unit)
{

    val state = viewModel.state

    LaunchedEffect(Unit) {

        viewModel.loadPersonaje(personaje)

    }

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) goToBack()
    }


    val eventos = PersonajeEvents(

        onChangeNombre = viewModel::onChangeNombre,
        //onChangePlaneta  = viewModel::onChangePlanetaDeOrigen,
        onChangePlaneta  = viewModel::onChangePlaneta,
        onChangeGenero = viewModel::onChangeGenero,
        onChangeFechaNac = viewModel::onChangeFechaNac,
        onChangeColorOjos = viewModel::onChangeColorDeOjos,
        onChangeIsInmortal = viewModel::onChangeIsInmortal,
        onConfirmar = viewModel::updatePersonaje,
    )




    PersonajeContent(modifier, state, eventos, "Edicion") //falta decir titulo
}











@Composable
fun PersonajeContent(modifier : Modifier = Modifier, state : PersonajeState,
                     events: PersonajeEvents, title : String)
{
    val scrollState = rememberScrollState()

    Column (

        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    )
    {

        Text(
            text = title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )

        IdField(state.id)
        NombreField(
            state.nombre, events.onChangeNombre,
            state.nombreError
        )
        //PlanetaField(state.planetaDeOrigen, events.onChangePlaneta)


        PlanetaField(
            selectedPlanetName = state.planetaDeOrigen,
            onPlanetSelected = events.onChangePlaneta
        )



        ColorOjosDropdown(state.colorDeOjos, onColorChange = events.onChangeColorOjos)



        FechaNacimientoField(state.fechanac, events.onChangeFechaNac)



        GeneroDropdown(state.genero, events.onChangeGenero)

        IsInmortal(state.IsInmortal, events.onChangeIsInmortal )


        Text(text = state.mensajeError, style = TextStyle(color = Color.Red))
        ConfirmarButton(enabled = true, title = "Confirmar",  onConfirmar = events.onConfirmar)

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
fun PersonajeScreenPreviewDaltonicoFuenteGrande() {

    StarwarsPersonajeTheme(colorBlind = true) {

        val fakeState = PersonajeState(
            id = 1,
            nombre = "Luke Skywalker",
            planetaDeOrigen = "Tatooine",
            genero = "Masculino",
            fechanac = "19 BBY",
            colorDeOjos = "Azules",
            IsInmortal = false,
            mensajeError = "",
            nombreError = NombreError.Ninguno,
            isSaved = false
        )

        val fakeEvents = PersonajeEvents(
            onChangeNombre = {},
            onChangePlaneta = {},
            onChangeGenero = {},
            onChangeFechaNac = {},
            onChangeColorOjos = {},
            onChangeIsInmortal = {},
            onConfirmar = {}
        )

        PersonajeContent(
            state = fakeState,
            events = fakeEvents,
            title = "Creación"
        )
    }
}

package com.example.starwarspersonaje.ui.home



import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.starwarspersonaje.ui.data.models.Personaje
import com.example.starwarspersonaje.ui.theme.screens.Info.AboutUsScreen
import com.example.starwarspersonaje.ui.theme.screens.Personajes.List.PersonajeListScreen
import com.example.starwarspersonaje.ui.theme.screens.Personajes.Personaje.PersonajeCreacion

import com.example.starwarspersonaje.ui.theme.screens.Personajes.Personaje.PersonajeEdicion
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


object Routes {
    const val LIST = "list"
    const val ADD= "añadir"

    const val ABOUTUS = "aboutUs"

    const val EDIT = "edit"

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostScreen(navController: NavHostController,modifier: Modifier) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.LIST,
        modifier = modifier
    ) {

        composable(

            route = Routes.ADD,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(400)) }

        ){ PersonajeCreacion(
            Modifier,
            viewModel = hiltViewModel(),
            goToBack = {navController.popBackStack()}

        ) } //falta poner creacion corrcto

        composable(route = Routes.LIST,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(400)) }

        ){ PersonajeListScreen(
            modifier = modifier,
            goToAdd = { navController.navigate(Routes.ADD) },
            goToEdit = {

                personaje ->

                navController.currentBackStackEntry?.
                savedStateHandle?.set("personaje", personaje)




                    navController.navigate(Routes.EDIT)



            },
            viewModel = hiltViewModel(),

        ) }


        composable(Routes.ABOUTUS,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(400)) }
        ){ AboutUsScreen() }

        composable(Routes.EDIT,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(400)) }
        ){

            val personaje = navController.previousBackStackEntry?.savedStateHandle?.get<Personaje>("personaje")

            personaje?.let()
            {
                PersonajeEdicion(
                    Modifier, hiltViewModel(),
                    personaje = personaje,
                    goToBack = {navController.popBackStack()}
                )
            }

        }



    }

}

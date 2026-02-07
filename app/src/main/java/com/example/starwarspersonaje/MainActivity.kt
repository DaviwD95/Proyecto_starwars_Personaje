package com.example.starwarspersonaje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.starwarspersonaje.ui.home.Routes
import com.example.starwarspersonaje.ui.theme.StarwarsPersonajeTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.starwarspersonaje.ui.home.NavHostScreen
import com.example.starwarspersonaje.ui.theme.screens.Info.AboutUsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity()  {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            StarwarsPersonajeTheme {

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {

                        ModalDrawerSheet {

                            Text(
                                text = "Menú Star Wars",
                                modifier = Modifier.padding(16.dp)
                            )

                            HorizontalDivider()

                            NavigationDrawerItem(
                                label = { Text("Listado Personajes") },
                                selected = false,
                                onClick = {
                                    navController.navigate(Routes.LIST)
                                    scope.launch { drawerState.close() }
                                }
                            )

                            NavigationDrawerItem(
                                label = { Text("Añadir Personaje") },
                                selected = false,
                                onClick = {
                                    navController.navigate(Routes.ADD)
                                    scope.launch { drawerState.close() }
                                }
                            )

                            NavigationDrawerItem(
                                label = { Text("Acerca de") },
                                selected = false,
                                onClick = {
                                    navController.navigate(Routes.ABOUTUS)
                                    scope.launch { drawerState.close() }
                                }
                            )
                        }
                    }
                ) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        contentWindowInsets = WindowInsets(0),

                        topBar = {

                            TopAppBar(
                                title = { Text("Personaje") },

                                navigationIcon = {

                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(Icons.Default.Menu, "menu")
                                    }

                                },

                                actions = {

                                    IconButton(onClick = { navController.navigate(Routes.ADD) }) {
                                        Icon(Icons.Default.Add, null)
                                    }

                                }
                            )
                        }
                    ) { innerPadding ->

                        NavHostScreen(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun OverflowMenu(

    onInfoClick:() -> Unit,

)
{
    var expanded by remember {mutableStateOf(false)}

    Box(
        //modifier = Modifier
        //  .fillMaxSize()
        //.padding(start = 15.dp)
        //.wrapContentSize(align = Alignment.TopStart),
        //contentAlignment = Alignment.Center
        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(
            onClick = {
                expanded = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Open Menu"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(

                text = {Text(text = "About Us")},
                onClick = onInfoClick,
                )



        }
    }
}

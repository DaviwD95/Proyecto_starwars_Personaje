package com.example.starwarspersonaje.ui.theme.screens.Info

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.starwarspersonaje.R

/**
 * Es la pantalla abous us con info necesaria
 */
@Composable
fun AboutUsScreen (){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A14))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
         )

    {

        Image(
            painter = painterResource(R.drawable.metal),

            contentDescription = "Foto de perfil  de creador",
            modifier = Modifier
                .size(150.dp)                 // tamaño de la imagen
                .clip(CircleShape)            // recorte circular
                .border(3.dp, Color.Yellow, CircleShape)

        )

        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(text = stringResource(R.string.NombreAboutUs),
            color = Color.Yellow)


        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(text = stringResource(R.string.InfoAboutUs),
            color = Color.Yellow)


        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(text = stringResource(R.string.CentroAboutUs),
            color = Color.Yellow)


        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


    }
}
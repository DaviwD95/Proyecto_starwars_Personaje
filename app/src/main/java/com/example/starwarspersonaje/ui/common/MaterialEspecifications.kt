package com.example.starwarspersonaje.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



/**
 * Clase que defione el estilo de una tarjeta
 *
 */

data class CardStyleDataClass (

    val elevation: Dp,
    val containerColor: Color,
    val padding : PaddingValues


)



val CardStyle = CardStyleDataClass(
    elevation = 9.dp,
    containerColor = Color.Cyan,
    padding = PaddingValues(horizontal = SpaceDimensiona.large, vertical = SpaceDimensiona.medium)
)



val LocalCardStyle = staticCompositionLocalOf { CardStyle }


object SpaceDimensiona
{
    val small = 4.dp
    val medium = 6.dp
    val large = 8.dp


}
object PaddingScreen
{
    val small = 4.dp
    val medium = 6.dp
    val large = 8.dp


}
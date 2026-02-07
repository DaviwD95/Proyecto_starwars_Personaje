package com.example.starwarspersonaje.ui.common



import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CardStyle(
    val elevation: Dp,
    val containerColor: Color,
    val padding: PaddingValues
)

val LocalCardStyle = staticCompositionLocalOf {
    CardStyle(
        elevation = 8.dp,
        containerColor = Color(0xFF1C1C1C), // gris oscuro Star Wars
        padding = PaddingValues(horizontal = AppSpacing.lg, vertical = AppSpacing.md)
    )
}

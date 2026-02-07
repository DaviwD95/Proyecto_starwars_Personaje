package com.example.starwarspersonaje.ui.theme.componentes

import androidx.compose.runtime.Composable

@Composable

fun ColorOjosDropdown(
    colorDeOjos: String,
    onColorChange: (String) -> Unit
) {
    DropDown(
        label = "Color de Ojos",
        options = listOf("Azul", "Marrón", "Verde", "Amarillo"),
        selectedOption = colorDeOjos,
        onOptionSelected = onColorChange
    )
}

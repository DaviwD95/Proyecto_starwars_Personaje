package com.example.starwarspersonaje.ui.theme.componentes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GeneroDropdown(
    genero: String,
    onGeneroChange: (String) -> Unit
) {
    DropDown(
        label = "Género",
        options = listOf("Male", "Female", "Hermaphrodite", "N/A"),
        selectedOption = genero,
        onOptionSelected = onGeneroChange
    )
}

@Preview(showBackground = true) //Usa el dropdown de color ojos, total tienen la misma funcion
@Composable
fun GeneroPreview() {
    var selected by remember { mutableStateOf("Azul") }
    DropDown(

        label = "Especie",
        options = listOf("Male", "Female", "Hermaphrodite", "N/A"),
        selectedOption = selected,
        onOptionSelected = { selected = it }

    )
}

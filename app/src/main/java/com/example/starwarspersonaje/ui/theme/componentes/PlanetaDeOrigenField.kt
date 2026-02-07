package com.example.starwarspersonaje.ui.theme.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarspersonaje.ui.data.models.Planet

/**
 * @Composable
 * fun PlanetaField(planeta: String, onChange: (String) -> Unit) {
 *     OutlinedTextField(
 *         value = planeta,
 *         onValueChange = onChange,
 *         label = { Text("Planeta de Origen") },
 *         singleLine = true,
 *         modifier = Modifier
 *             .sizeIn(maxWidth = 488.dp)
 *             .fillMaxWidth()
 *     )
 * }
 *
 * @Preview(showBackground = true)
 * @Composable
 * fun PlanetaFieldPreview() {
 *     PlanetaField(planeta = "Tierra", onChange = {})
 * }
 */



@Composable
fun PlanetaField(selectedPlanetName: String, onPlanetSelected: (Planet) -> Unit
) {

    val planetasPredefinidos = listOf(
        Planet(id = 1, name = "Tatooine"),
        Planet(id = 2, name = "Alderaan"),
        Planet(id = 3, name = "Naboo"),
        Planet(id = 4, name = "Coruscant"),
        Planet(id = 5, name = "Dagobah")
    )
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            value = selectedPlanetName,
            onValueChange = {},
            label = { Text("Planeta de origen") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            planetasPredefinidos.forEach { planet ->
                DropdownMenuItem(
                    text = { Text(planet.name) },
                    onClick = {
                        onPlanetSelected(planet)
                        expanded = false
                    }
                )
            }
        }
    }
}


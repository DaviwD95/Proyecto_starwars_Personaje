package com.example.starwarspersonaje.ui.theme.componentes



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FechaNacimientoField(fecha: String, onChange: (String) -> Unit) {

    var errorMessage by remember { mutableStateOf("") }

    OutlinedTextField(

        value = fecha,
        onValueChange = { valorNuevo ->
            val regex = Regex("^\\d{2}-\\d{2}-\\d{4}$")

            if(!valorNuevo.matches(regex) && valorNuevo.isNotEmpty())
            {
                errorMessage = "Error, formato de fecha incorrecto  (usa DD-MM-YYYY)"
            }else
            {
                errorMessage = ""

            }

            onChange(valorNuevo)


        },
        label = { Text("Fecha de Nacimiento") },
        singleLine = true,
        modifier = Modifier
            .sizeIn(maxWidth = 488.dp)
            .fillMaxWidth(),

        isError = errorMessage.isNotEmpty(),
        supportingText = {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage)
            }
        }

    )
}

@Preview(showBackground = true)
@Composable
fun FechaNacimientoFieldPreview() {
    FechaNacimientoField(fecha = "1990-08-15", onChange = {})
}

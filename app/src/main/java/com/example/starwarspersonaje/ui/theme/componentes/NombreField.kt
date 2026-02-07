package com.example.starwarspersonaje.ui.theme.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarspersonaje.ui.data.models.Errors.NombreError

@Composable
fun NombreField(nombre: String, onChange: (String) -> Unit, nombreError: NombreError) {

    var errorMessage by remember { mutableStateOf("") }

    OutlinedTextField(

        value = nombre,

       onValueChange = { Valornuevo ->

           onChange(Valornuevo)
        },

        label = { Text("Nombre") },
        singleLine = true,
        modifier = Modifier
            .sizeIn(maxWidth = 488.dp)
            .fillMaxWidth(),

        isError = nombreError.isError(),

        supportingText = {
            if (nombreError.isError()) {
                Text(text = nombreError.message!!)
            }
        }

    )
}

@Preview(showBackground = true)
@Composable
fun NombreFieldPreview() {
   // NombreField(nombre = "Arturito", onChange = {})
}

package com.example.starwarspersonaje.ui.theme.componentes



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IdField(id: Int) {
    OutlinedTextField(

        value = id.toString(),
        onValueChange = {}, // No editable
        label = { Text(text = "ID") },
        singleLine = true,
        modifier = Modifier
            .sizeIn(maxWidth = 488.dp)
            .fillMaxWidth(),
        enabled = false // Se supone que esto hace que no sea editbñe
    )
}

@Preview(showBackground = true)
@Composable
fun IdFieldPreview() {
    IdField(
        id = 1 // ejemplo de ID
    )
}

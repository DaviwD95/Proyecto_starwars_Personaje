package com.example.starwarspersonaje.ui.theme.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarspersonaje.ui.theme.StarwarsPersonajeTheme

@Composable
fun ConfirmarButton(enabled: Boolean = true, title: String, onConfirmar : () -> Unit) {

    Button(
        onClick = onConfirmar,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    )

    {
        Text(text = title)
    }

}

@Preview(showBackground = true)
@Composable
fun ConfirmarCreacionPreview() {
    StarwarsPersonajeTheme {
        ConfirmarButton(
            title = "confirmar",
            enabled = TODO(),
            onConfirmar = TODO()
        )
    }
}


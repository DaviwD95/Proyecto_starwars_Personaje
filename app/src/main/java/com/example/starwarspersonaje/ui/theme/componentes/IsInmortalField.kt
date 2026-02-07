package com.example.starwarspersonaje.ui.theme.componentes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.starwarspersonaje.R
import com.example.starwarspersonaje.ui.theme.StarwarsPersonajeTheme

@Composable
fun IsInmortal( isInmortal: Boolean, onCheckedChange: (Boolean) -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp))
    {

        Checkbox(
            checked = isInmortal,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = stringResource(R.string.inmortal),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IsInmortalPreview() {
    StarwarsPersonajeTheme {
        IsInmortal(false, onCheckedChange = {})
    }
}
package dev.doblezeta.crimsontriangle.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun LinkText(
    enlace: String,
    onEnlaceCambios: (String) -> Unit
){
    TextField(
        value = enlace,
        onValueChange = {
            onEnlaceCambios(it)
        },
        label = {
            Text("Inserte enlace de Descarga")
        },
        placeholder = {
            Text("")
        }
    )
}
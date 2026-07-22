package dev.doblezeta.crimsontriangle.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectorDeFormatos(
    formatoSeleccionado : String,
    onFormatoSeleccionado : (String) -> Unit
){
    var ctrlFormatoExpandido by remember {
        mutableStateOf(false)
    }
    val lsformatos = listOf(
        "Mejor Calidad",
        "Solo audio (MP3)",
        "MP4 1080p",
        "MP4 720p",
        "MP4 480p",
        "Playlist MP4",
        "Playlist MP3",
        "WEBM"
    )

    Spacer(modifier = Modifier.height(10.dp))

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                ctrlFormatoExpandido = !ctrlFormatoExpandido
            }) {
            Text("Descargando en: $formatoSeleccionado")
        }
        DropdownMenu(expanded = ctrlFormatoExpandido, onDismissRequest = {
            ctrlFormatoExpandido = false
        }) {
            for (formato in lsformatos) {
                DropdownMenuItem(
                    text = {
                        Text(formato)
                    },
                    onClick = {
                        onFormatoSeleccionado(formato)
                        ctrlFormatoExpandido = false
                    }
                )
            }

        }
    }
}

package dev.doblezeta.crimsontriangle.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BotonesDeAbajo(
    onAbrir: () -> Unit
){
    Row(modifier = Modifier.padding(all = 10.dp)) {

        Button(onClick = onAbrir) {
            Text("Abrir")
        }

        Spacer(modifier = Modifier.width(80.dp))


        AboutButton()
    }
}
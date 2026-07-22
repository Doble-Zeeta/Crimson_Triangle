package dev.doblezeta.crimsontriangle.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.doblezeta.crimsontriangle.R


@Composable
fun AboutButton() {
    var ctrlAbout by remember {
        mutableStateOf(false)
    }
    Button(onClick = {
        ctrlAbout = true
    }) {
        Text("About")
    }

if (ctrlAbout) {
    AboutDialog(
        onCerrar = { ctrlAbout = false }
    )
}

}
@Composable
fun AboutDialog(
    onCerrar: ()-> Unit
){

    val context = LocalContext.current
    val name = "Bienvenido a CrimsonTriangle"

    AlertDialog(
        onDismissRequest = {
            onCerrar()
        },
        confirmButton = {
            Button(onClick = { onCerrar() }) {
                Text("Cerrar")
            }
        },
        title = {
            Column() {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo Crimson Triangle",
                    modifier = Modifier.size(64.dp).align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(name)
            }
        },
        text = {
            Column() {
                Text(
                    "Creado por Doble__Zeta_Dev"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text("Descargador de videos con en yt-dlp.")
                Spacer(modifier = Modifier.height(10.dp))
                Text("Seguime en: ")

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "GitHub",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(true)
                    {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/Doble-Zeeta")
                        )
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Twitter",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(true)
                    {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://x.com/Doble__Zeta")
                        )
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "YouTube",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(true)
                    {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/@Doble_Zeta")
                        )
                        intent.addCategory(Intent.CATEGORY_BROWSABLE)
                        context.startActivity(intent)
                    }
                )

                Text(
                    "______________",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(20.dp))
                Text("Version 0.1a", color = Color.Gray)
            }
        })
}
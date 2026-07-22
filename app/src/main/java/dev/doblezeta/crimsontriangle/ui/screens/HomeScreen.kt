package dev.doblezeta.crimsontriangle.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.doblezeta.crimsontriangle.R
import dev.doblezeta.crimsontriangle.ui.components.AboutButton
import dev.doblezeta.crimsontriangle.ui.components.LinkText
import dev.doblezeta.crimsontriangle.ui.components.SelectorDeFormatos
import dev.doblezeta.crimsontriangle.ui.theme.CrimsonTriangleTheme
import kotlinx.coroutines.launch


@Composable
fun PantalladeInicio(modifier: Modifier = Modifier) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

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
    var ctrlFormatoSeleccionado by remember {
        mutableStateOf(lsformatos[0])
    }


    Scaffold(snackbarHost = {

        SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color(0x801E1E1E),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.border(
                    BorderStroke(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
            )
        }
    }
    ) {

            paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            val name = "Bienvenido a CrimsonTriangle"

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Crimson Triangle",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(name)



            Spacer(modifier = Modifier.height(25.dp))

            var enlace by remember {
                mutableStateOf(value = "")
            }


            LinkText(
                enlace = enlace,
                onEnlaceCambios = {
                    enlace = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Formato", modifier = Modifier.align(Alignment.Start)
                    .padding(start = 45.dp)
            )

            SelectorDeFormatos(
                formatoSeleccionado = ctrlFormatoSeleccionado,
                onFormatoSeleccionado = { ctrlFormatoSeleccionado = it }

            )

            Spacer(modifier = Modifier.height(20.dp))



            Button(
                onClick = {
                    if (enlace.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Debe ingresar un link primero"
                            )
                        }

                    }
                },
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            ) {
                Text("Descargar")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.padding(all = 10.dp)) {

                Button(onClick = {}) {
                    Text("Abrir")
                }

                Spacer(modifier = Modifier.width(80.dp))


                AboutButton()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantalladeInicioPreview() {
    CrimsonTriangleTheme {
        PantalladeInicio()
    }
}

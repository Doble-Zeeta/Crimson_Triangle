package dev.doblezeta.crimsontriangle.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.doblezeta.crimsontriangle.R
import dev.doblezeta.crimsontriangle.ui.components.BotonesDeAbajo
import dev.doblezeta.crimsontriangle.ui.components.BtnDescargar
import dev.doblezeta.crimsontriangle.ui.components.LinkText
import dev.doblezeta.crimsontriangle.ui.components.SelectorDeFormatos
import dev.doblezeta.crimsontriangle.ui.theme.CrimsonTriangleTheme
import dev.doblezeta.crimsontriangle.ui.utils.abrirDescargasFolder
import dev.doblezeta.crimsontriangle.ui.utils.Descargador
import dev.doblezeta.crimsontriangle.ui.utils.EstadoDeDescarga
import kotlinx.coroutines.launch


@Composable
fun PantalladeInicio(modifier: Modifier = Modifier,
                     enlaceInicial: String = ""
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val lsformatos = listOf(
        "Mejor Calidad",
        "MP4 1080p",
        "MP4 720p",
        "MP4 480p",
        "Audio Flac",
        "Audio MP3",
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
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Crimson Triangle",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(name)



            Spacer(modifier = Modifier.height(25.dp))

            var enlace by rememberSaveable(enlaceInicial) {
                mutableStateOf(value = enlaceInicial)
            }


            LinkText(
                enlace = enlace,
                onEnlaceCambios = {
                    enlace = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Formato", modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 45.dp)
            )

            SelectorDeFormatos(
                formatoSeleccionado = ctrlFormatoSeleccionado,
                onFormatoSeleccionado = { ctrlFormatoSeleccionado = it }

            )

            Spacer(modifier = Modifier.height(20.dp))

            BtnDescargar(
                onClick = {
                    if (enlace.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Debe ingresar un link primero"
                            )
                        }
                        return@BtnDescargar
                    }

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Descargando..",
                            withDismissAction = true
                        )
                    }

                    Thread{
                        try {
                            Descargador.descargar(
                                url = enlace,
                                formato = ctrlFormatoSeleccionado,
                                onEstado = {
                                    estado ->

                                    scope.launch {
                                        snackbarHostState.currentSnackbarData?.dismiss()

                                        val mensaje = when (estado) {

                                            EstadoDeDescarga.Preparando ->
                                                "Preparando descarga.."

                                            is EstadoDeDescarga.Descargando ->
                                                "Descargando ${estado.nombre}"

                                            EstadoDeDescarga.Uniendo ->
                                                "Uniendo audio y video.."

                                            EstadoDeDescarga.Extrayendo ->
                                                "Extrayendo audio.."

                                            EstadoDeDescarga.Finalizando ->
                                                "Finalizando.."

                                            EstadoDeDescarga.Completada ->
                                                "Descarga Completada"

                                            is EstadoDeDescarga.Error ->
                                                estado.mensaje
                                        }

                                        snackbarHostState.showSnackbar(
                                            message = mensaje,
                                            withDismissAction = true
                                        )

                                    }

                                }
                            )
                        }
                        catch (e: Exception) {
                            Log.e("CrimsonTriangle", "DOWNLOAD ERROR", e)
                        }
                    }.start()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            BotonesDeAbajo(
                onAbrir = {
                    abrirDescargasFolder(context)
                }
            )
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

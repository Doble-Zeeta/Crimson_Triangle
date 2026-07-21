package dev.doblezeta.crimsontriangle.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.doblezeta.crimsontriangle.ui.theme.CrimsonTriangleTheme



@Composable
fun PantalladeInicio(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize().padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        val firstName = "Bienvenido a CrimsonTriangle"
        val altName = "Creado por Doble__Zeta_Dev"
        var name by remember { mutableStateOf(false) }
        Text(if (!name)
            firstName
        else
            altName
        )


        Spacer(modifier = Modifier.height(20.dp))

        var enlace by remember {
            mutableStateOf(value = "")
        }


        TextField(
            value = enlace, onValueChange = {
                enlace = it
            },
            placeholder = {
                Text("Inserte enlace de Descarga")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Formato")

        Spacer(modifier = Modifier.height(10.dp))
        //algo
        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.padding(all = 10.dp)) {

            Button(onClick = {}){
                Text("Abrir")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = {
                name = !name

            }) {
                Text("About")
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
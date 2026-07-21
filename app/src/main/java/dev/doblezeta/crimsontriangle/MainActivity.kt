package dev.doblezeta.crimsontriangle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.doblezeta.crimsontriangle.ui.theme.CrimsonTriangleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrimsonTriangleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantalladeInicio(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

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
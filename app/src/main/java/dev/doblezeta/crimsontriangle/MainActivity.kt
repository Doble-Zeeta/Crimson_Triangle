package dev.doblezeta.crimsontriangle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.yausername.youtubedl_android.YoutubeDL
import dev.doblezeta.crimsontriangle.ui.screens.PantalladeInicio
import dev.doblezeta.crimsontriangle.ui.theme.CrimsonTriangleTheme
import java.io.File

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        try {
            val dir = File(applicationInfo.nativeLibraryDir)

            Log.e("LIBS", "DIR = ${dir.absolutePath}")
            Log.e("LIBS", "exists = ${dir.exists()}")
            Log.e("LIBS", "isDirectory = ${dir.isDirectory}")

            val files = dir.listFiles()

            Log.e("LIBS", "files = ${files?.size}")

            files?.forEach {
                Log.e("LIBS", it.name)
            }

            YoutubeDL.getInstance().init(this)
            android.util.Log.d("CrimsonTriangle", "YoutubeDL inicializado")
        } catch (e: Exception) {
            android.util.Log.e("CrimsonTriangle", "INIT ERROR", e)
        }

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

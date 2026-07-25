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
import android.content.Intent
import com.yausername.youtubedl_android.YoutubeDL
import dev.doblezeta.crimsontriangle.ui.screens.PantalladeInicio
import dev.doblezeta.crimsontriangle.ui.theme.CrimsonTriangleTheme
import com.yausername.ffmpeg.FFmpeg
import java.io.File

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        try {
            val version = YoutubeDL.getInstance().version(this)
            Log.d("CrimsonTriangle", "yt-dlp: $version")
        } catch (e: Exception) {
            Log.e("CrimsonTriangle", "No pude obtener la versión", e)
        }
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
            FFmpeg.getInstance().init(this)
            android.util.Log.d("CrimsonTriangle", "FFMPEG: ${FFmpeg.getInstance()}")

            Thread {
                try {
                    val estado = YoutubeDL.getInstance()
                        .updateYoutubeDL(this)

                    Log.d("YT", "Update: $estado")

                    Log.d(
                        "YT",
                        "Version: ${
                            YoutubeDL.getInstance().version(this)
                        }"
                    )
                } catch (e: Exception) {
                    Log.e("YT", "No pudo actualizar", e)
                }
            }.start()
        } catch (e: Exception) {
            android.util.Log.e("CrimsonTriangle", "INIT ERROR", e)
        }

        enableEdgeToEdge()

        val compartirEnlace = if (
            intent?.action == Intent.ACTION_SEND &&
            intent.type == "text/plain"
        ){
            intent.getStringExtra(Intent.EXTRA_TEXT) ?: ""
        }
        else{
            ""
        }

        setContent {
            CrimsonTriangleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantalladeInicio(
                        modifier = Modifier.padding(innerPadding),
                        enlaceInicial = compartirEnlace
                    )
                }
            }
        }
    }
}

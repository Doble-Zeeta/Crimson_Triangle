package dev.doblezeta.crimsontriangle.ui.utils

import android.os.Environment
import android.util.Log
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLRequest
import java.io.File

object Descargador{

private const val NOMBRE_MAXIMO = 80

    private fun limpiarNombre(nombre: String): String =
        nombre
            .replace(Regex("""[\\/:*?"<>|]"""), " ")
            .replace("\n", " ")
            .replace("\r", " ")
            .replace(Regex("\\s+"), " ")
            .trim()
    private fun acortarNombre(nombre: String): String {
        return if (nombre.length > NOMBRE_MAXIMO) {
            nombre.take(NOMBRE_MAXIMO).trimEnd() + "..."
        }
        else{
            nombre
        }
    }



    fun descargar(
        url: String,
        formato: String,
        onEstado: (EstadoDeDescarga) -> Unit
    ){
        val carpeta = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
        ),
            "CrimsonTriangle"
        )
        carpeta.mkdirs()



        try {

        onEstado(EstadoDeDescarga.Preparando)
            val infoRequest = YoutubeDLRequest(url)
            val request = YoutubeDLRequest(url)
            if (url.contains("youtube") || url.contains("youtu.be")) {
                infoRequest.addOption("--extractor-args")
                infoRequest.addOption("youtube:player-client=android")
                request.addOption("--extractor-args")
                request.addOption("youtube:player-client=android")

            }

            infoRequest.addOption("--no-update")
            infoRequest.addOption("--print")
            infoRequest.addOption("%(title)s")

            val tituloOriginal = YoutubeDL.getInstance()
                .execute(infoRequest)
                .out
                .trim()

            val nombreArchivo = acortarNombre(
                limpiarNombre(tituloOriginal)
            )

            request.addOption("--no-update")
            when (formato) {
                "Mejor Calidad" ->{
                    request.addOption("-f",
                    "bv*+ba/b"
                    )
                }

                    "Audio MP3" -> {
                        request.addOption("-f", "ba/b")

                        request.addOption("-x")

                    request.addOption("--audio-format",
                        "mp3")

                    request.addOption("--audio-quality","0")
                }

            }

            request.addOption("--embed-metadata")

            request.addOption("--embed-thumbnail")

            request.addOption("--add-metadata")

            request.addOption(
                "-o",
                "${carpeta.absolutePath}/$nombreArchivo.%(ext)s"
            )
            Log.d("CT", "ANTES DE DESCARGAR")

        YoutubeDL.getInstance().execute(
            request,
            null
        ){
            progress, etaInSeconds, line ->

            android.util.Log.d("YT-DLP", "Progress: $progress%")
            android.util.Log.d("YT-DLP", "ETA: $etaInSeconds")
            android.util.Log.d("YT-DLP", line)

            when {
                line.startsWith("[download] Destination:") -> {


                    onEstado(
                        EstadoDeDescarga.Descargando(nombreArchivo)
                    )

                }
                line.startsWith("[Merger]") -> {
                    onEstado(
                        EstadoDeDescarga.Uniendo
                    )
                }
                line.startsWith("[ExtractAudio]") -> {
                    onEstado(
                        EstadoDeDescarga.Extrayendo
                    )
                }
                line.startsWith("[download] 100") -> {
                    onEstado(
                        EstadoDeDescarga.Finalizando
                    )
                }
            }
        }
            onEstado(EstadoDeDescarga.Completada)
        }

        catch (e: Exception) {
            onEstado(
                EstadoDeDescarga.Error(
                    e.message ?: "Error desconocido"
                )
            )
        }
    }
}

package dev.doblezeta.crimsontriangle.ui.utils

import android.content.Context
import android.os.Environment
import com.yausername.youtubedl_android.DownloadProgressCallback
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLRequest
import java.io.File

object Descargador{

    fun descargar(
        context: Context,
        url: String
    ){
        val carpeta = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
        ),
            "CrimsonTriangle"
        )
        carpeta.mkdirs()

        val request = YoutubeDLRequest(url)

        request.addOption("--extractor-args")
        request.addOption("youtube:player-client=android")

        request.addOption(
            "-o",
            "${carpeta.absolutePath}/%(title)s.%(ext)s"
        )
        YoutubeDL.getInstance().execute(
            request,
            null
        ){
            progress, etaInSeconds, line ->
            android.util.Log.d("YT-DLP", "Progress: $progress%")
            android.util.Log.d("YT-DLP", "ETA: $etaInSeconds")
            android.util.Log.d("YT-DLP", line)
        }
    }

}

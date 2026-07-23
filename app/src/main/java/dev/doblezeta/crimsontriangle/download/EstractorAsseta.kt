package dev.doblezeta.crimsontriangle.download

import android.content.Context
import java.io.File

object EstractorAsseta{
    fun extractor(context: Context, assetName: String): File {
        val outFile = File(context.filesDir, assetName)

        if (!outFile.exists()) {
            context.assets.open(assetName).use { inputStream ->
                outFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            outFile.setExecutable(true)
        }
    return outFile
    }
}
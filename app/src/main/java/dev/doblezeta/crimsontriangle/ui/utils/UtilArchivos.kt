package dev.doblezeta.crimsontriangle.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.platform.LocalContext

fun abrirDescargasFolder(
    context: Context
){
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(
                Uri.parse("content://com.android.externalstorage.documents/document/primary:Download"),
                DocumentsContract.Document.MIME_TYPE_DIR
            )
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }
    catch (e: Exception){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        context.startActivity(intent)
    }
}
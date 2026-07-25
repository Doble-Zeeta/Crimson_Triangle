package dev.doblezeta.crimsontriangle.ui.utils

sealed class EstadoDeDescarga{
    data object Preparando : EstadoDeDescarga()
    data class  Descargando(
        val nombre: String
    ) : EstadoDeDescarga()

    data object Uniendo : EstadoDeDescarga()

    data object Extrayendo : EstadoDeDescarga()

    data object Finalizando : EstadoDeDescarga()

    data object Completada : EstadoDeDescarga()

    data class Error(
        val mensaje: String
    ) : EstadoDeDescarga()

}
package com.example.maltaexplorer.model

enum class TipoServicioIcono {
    BARCO, SNORKEL, BUS, PARKING
}

data class Servicio(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val tipoIcono: TipoServicioIcono
)

data class CondicionesClima(
    val estado: String,
    val agua: String,
    val oleaje: String,
    val temperatura: String,
    val uv: String
)

data class Lugar(
    val id: String,
    val titulo: String,
    val categoria: String,
    val ubicacion: String,
    val valoracion: Double,
    val numResenas: String,
    val descripcionCorta: String,
    val descripcionCompleta: String,
    val imagenUrl: String,
    val fotosGaleria: List<String>,
    val esFavorito: Boolean = false,
    val condicionesHoy: CondicionesClima,
    val servicios: List<Servicio>
)

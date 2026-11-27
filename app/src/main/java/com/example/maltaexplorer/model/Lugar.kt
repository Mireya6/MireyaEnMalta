package com.example.maltaexplorer.model

// Define los tipos de icono disponibles para representar cada servicio
enum class TipoServicioIcono {
    // Representa traslados o excursiones en barco
    BARCO,
    // Representa actividades de snorkel o baño en el agua
    SNORKEL,
    // Representa conexión o parada de autobús
    BUS,
    // Representa disponibilidad de aparcamiento
    PARKING
}

// Modela la información de un servicio asociado a un lugar turístico
data class Servicio(
    // Identificador único del servicio
    val id: String,
    // Nombre corto o título del servicio
    val titulo: String,
    // Breve descripción del servicio
    val descripcion: String,
    // Tipo de icono que identifica visualmente el servicio
    val tipoIcono: TipoServicioIcono
)

// Modela las condiciones climáticas y del entorno registradas para el día
data class CondicionesClima(
    // Estado general de las condiciones del lugar
    val estado: String,
    // Estado o temperatura del agua
    val agua: String,
    // Nivel o descripción del oleaje
    val oleaje: String,
    // Temperatura ambiental actual
    val temperatura: String,
    // Nivel de radiación ultravioleta
    val uv: String
)

// Modela la entidad principal de un lugar turístico en MaltaExplorer
data class Lugar(
    // Identificador único del lugar
    val id: String,
    // Nombre principal del lugar
    val titulo: String,
    // Categoría turística a la que pertenece el lugar
    val categoria: String,
    // Ubicación geográfica o localidad dentro del archipiélago
    val ubicacion: String,
    // Puntuación media obtenida por el lugar
    val valoracion: Double,
    // Cantidad de reseñas en formato de texto
    val numResenas: String,
    // Resumen breve para tarjetas o listados
    val descripcionCorta: String,
    // Descripción detallada para la pantalla de detalle
    val descripcionCompleta: String,
    // URL de la fotografía principal del lugar
    val imagenUrl: String,
    // Lista de URLs de imágenes para la galería
    val fotosGaleria: List<String>,
    // Indica si el lugar está marcado como favorito por el usuario
    val esFavorito: Boolean = false,
    // Condiciones climáticas y del agua correspondientes a hoy
    val condicionesHoy: CondicionesClima,
    // Lista de servicios disponibles en el lugar
    val servicios: List<Servicio>
)
package com.example.maltaexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.maltaexplorer.model.CondicionesClima
import com.example.maltaexplorer.model.Lugar
import com.example.maltaexplorer.model.Servicio
import com.example.maltaexplorer.model.TipoServicioIcono
import com.example.maltaexplorer.ui.pages.PantallaDetalleLugar
import com.example.maltaexplorer.ui.pages.PantallaDetalleLugarData
import com.example.maltaexplorer.ui.theme.MaltaExplorerTheme

// Declara la actividad principal de la aplicación basada en ComponentActivity
class MainActivity : ComponentActivity() {
    // Sobrescribe el método onCreate al crearse la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama a la implementación de ComponentActivity para inicializar la actividad
        super.onCreate(savedInstanceState)

        // Crea el objeto Lugar inicial con datos de ejemplo para probar la pantalla
        val lugarInicial = Lugar(
            // Identificador único del lugar de ejemplo
            id = "1",
            // Título principal del lugar de ejemplo
            titulo = "Blue Lagoon (Laguna Azul)",
            // Categoría turística del lugar de ejemplo
            categoria = "Playas y calas",
            // Ubicación geográfica del lugar de ejemplo
            ubicacion = "Isla de Comino, Malta",
            // Valoración numérica media del lugar
            valoracion = 4.8,
            // Número de reseñas en formato texto
            numResenas = "2.450",
            // Descripción corta para resúmenes y listados
            descripcionCorta = "Aguas cristalinas turquesas entre Comino y Gozo.",
            // Descripción completa para la pantalla de detalle
            descripcionCompleta = "La Laguna Azul es el rincón más icónico y deslumbrante del archipiélago maltés. Encajada entre la deshabitada Isla de Gozo y el islote de Comino, forma una espectacular piscina natural de aguas turquesas brillantes y fondo de arena blanca calcárea.",
            // URL de la fotografía principal del lugar
            imagenUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5",
            // Lista de fotografías para la galería del lugar
            fotosGaleria = listOf(
                // Primera imagen de la galería
                "https://images.unsplash.com/photo-1544551763-46a013bb70d5",
                // Segunda imagen de la galería
                "https://images.unsplash.com/photo-1507525428034-b723cf961d3e",
                // Tercera imagen de la galería
                "https://images.unsplash.com/photo-1519046904884-53103b34b206",
                // Cuarta imagen de la galería
                "https://images.unsplash.com/photo-1506744038136-46273834b3fb"
            ),
            // Estado inicial de favorito del lugar
            esFavorito = true,
            // Condiciones climáticas y del entorno para hoy
            condicionesHoy = CondicionesClima(
                // Estado general de las condiciones
                estado = "Óptimo",
                // Estado del agua
                agua = "Agua calma",
                // Estado del oleaje
                oleaje = "Sin olas",
                // Temperatura ambiental
                temperatura = "28°C",
                // Nivel de radiación ultravioleta
                uv = "UV 7 (Alto)"
            ),
            // Lista de servicios disponibles en el destino
            servicios = listOf(
                // Servicio de acceso en barco
                Servicio("1", "Acceso en barco", "Ferry regular", TipoServicioIcono.BARCO),
                // Servicio de snorkel y buceo
                Servicio("2", "Snorkel y buceo", "Aguas cristalinas", TipoServicioIcono.SNORKEL),
                // Servicio de autobús cercano
                Servicio("3", "Autobús cercano", "Línea C22", TipoServicioIcono.BUS)
            )
        )

        // Configura el árbol de composables de la actividad
        setContent {
            // Mantiene el estado reactivo del lugar de ejemplo durante la sesión en pantalla
            var lugarEstado by remember { mutableStateOf(lugarInicial) }

            // Aplica el tema MaterialTheme de la aplicación
            MaltaExplorerTheme {
                // Superficie base que ocupa toda la pantalla con el color de fondo del tema
                Surface(
                    // Ocupa el ancho y alto completos de la pantalla
                    modifier = Modifier.fillMaxSize(),
                    // Aplica el color de fondo definido en el tema
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Renderiza la pantalla de detalle encapsulando datos y callbacks en PantallaDetalleLugarData (C1)
                    PantallaDetalleLugar(
                        // Construye PantallaDetalleLugarData con el estado actual y las acciones
                        data = PantallaDetalleLugarData(
                            // Pasa el objeto Lugar actual
                            lugar = lugarEstado,
                            // Acción al pulsar volver
                            onVolverClick = { },
                            // Acción al pulsar compartir
                            onCompartirClick = { },
                            // Alterna el estado esFavorito al pulsar el botón correspondiente
                            onToggleFavorito = {
                                lugarEstado = lugarEstado.copy(esFavorito = !lugarEstado.esFavorito)
                            },
                            // Acción al pulsar ver en mapa
                            onVerEnMapaClick = { }
                        )
                    )
                }
            }
        }
    }
}
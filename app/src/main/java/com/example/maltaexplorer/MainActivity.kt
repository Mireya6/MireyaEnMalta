package com.example.maltaexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.maltaexplorer.model.*
import com.example.maltaexplorer.ui.pages.PantallaDetalleLugar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datos mock para probar la interfaz
        val lugarEjemplo = Lugar(
            id = "1",
            titulo = "Blue Lagoon (Laguna Azul)",
            categoria = "Playas y calas",
            ubicacion = "Isla de Comino, Malta",
            valoracion = 4.8,
            numResenas = "2.450",
            descripcionCorta = "Aguas cristalinas turquesas entre Comino y Gozo.",
            descripcionCompleta = "La Laguna Azul es el rincón más icónico y deslumbrante del archipiélago maltés. Encajada entre la deshabitada Isla de Comino y el islote de Cominotto, forma una espectacular piscina natural de aguas turquesas brillantes y fondo de arena blanca calcárea.",
            imagenUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5",
            fotosGaleria = listOf(
                "https://images.unsplash.com/photo-1544551763-46a013bb70d5",
                "https://images.unsplash.com/photo-1507525428034-b723cf961d3e",
                "https://images.unsplash.com/photo-1519046904884-53103b34b206",
                "https://images.unsplash.com/photo-1506744038136-46273834b3fb"
            ),
            esFavorito = true,
            condicionesHoy = CondicionesClima(
                estado = "Óptimo",
                agua = "Agua calma",
                oleaje = "Sin olas",
                temperatura = "28°C",
                uv = "UV 7 (Alto)"
            ),
            servicios = listOf(
                Servicio("1", "Acceso en barco", "Ferry regular", TipoServicioIcono.BARCO),
                Servicio("2", "Snorkel y buceo", "Aguas cristalinas", TipoServicioIcono.SNORKEL),
                Servicio("3", "Autobús cercano", "Línea C22", TipoServicioIcono.BUS)
            )
        )

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaDetalleLugar(
                        lugar = lugarEjemplo,
                        onVolverClick = { },
                        onCompartirClick = { },
                        onToggleFavorito = { },
                        onVerEnMapaClick = { }
                    )
                }
            }
        }
    }
}
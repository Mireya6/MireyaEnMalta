package com.example.maltaexplorer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.maltaexplorer.R
import com.example.maltaexplorer.model.CondicionesClima
import com.example.maltaexplorer.model.Lugar


// Data class que agrupa los datos y callbacks de TarjetaLugar (cumpliendo C1)
data class TarjetaLugarData(
    // Objeto Lugar con toda la información que se muestra en la tarjeta
    val lugar: Lugar,
    // Callback ejecutado al pulsar la tarjeta o el botón "Ver más"
    val onVerMasClick: (String) -> Unit = {},
    // Callback ejecutado al pulsar el icono de favorito
    val onToggleFavorito: (String) -> Unit = {}
)

// Declara la función Composable reutilizable que pinta la tarjeta de un lugar turístico
@Composable
fun TarjetaLugar(
    // Datos y acciones encapsulados en TarjetaLugarData
    data: TarjetaLugarData,
    // Modificador opcional aplicado en la raíz del componente (cumpliendo C2 y C3)
    modifier: Modifier = Modifier
) {
    // Extrae la referencia a Lugar desde data para un acceso más limpio
    val lugar = data.lugar

    // Tarjeta principal de Material 3 con bordes redondeados y elevación
    Card(
        // Aplica el modifier externo, ocupa todo el ancho y permite pulsar la tarjeta
        modifier = modifier
            // Ocupa el ancho máximo disponible
            .fillMaxWidth()
            // Dispara onVerMasClick pasando el identificador del lugar
            .clickable { data.onVerMasClick(lugar.id) },
        // Redondea las esquinas de la tarjeta a 16 dp
        shape = RoundedCornerShape(16.dp),
        // Aplica sombra de elevación de 4 dp
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        // Usa el color de superficie del tema actual
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        // Columna principal que apila la imagen superior y el bloque inferior de información
        Column(modifier = Modifier.fillMaxWidth()) {

            // Contenedor Box para superponer la categoría y el botón de favorito sobre la imagen
            Box(
                // Ocupa todo el ancho y fija una altura de 200 dp
                modifier = Modifier
                    // Ancho completo
                    .fillMaxWidth()
                    // Altura fija de 200 dp
                    .height(200.dp)
            ) {
                // Carga y muestra de forma asíncrona la fotografía principal del lugar
                AsyncImage(
                    // URL de la imagen del lugar
                    model = lugar.imagenUrl,
                    // Descripción de accesibilidad usando el título del lugar
                    contentDescription = lugar.titulo,
                    // Recorta proporcionalmente para llenar todo el espacio disponible
                    contentScale = ContentScale.Crop,
                    // Ocupa todo el ancho y alto del Box
                    modifier = Modifier.fillMaxSize()
                )

                // Etiqueta flotante con la categoría del lugar en la esquina superior izquierda
                Surface(
                    // Esquinas redondeadas de 8 dp
                    shape = RoundedCornerShape(8.dp),
                    // Fondo semitransparente derivado de onSurface del tema
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    // Posiciona arriba a la izquierda con margen exterior de 12 dp
                    modifier = Modifier
                        // Alineación superior izquierda
                        .align(Alignment.TopStart)
                        // Margen de 12 dp
                        .padding(12.dp)
                ) {
                    // Texto de la categoría en mayúsculas
                    Text(
                        // Convierte la categoría a mayúsculas
                        text = lugar.categoria.uppercase(),
                        // Estilo tipográfico de etiqueta pequeña
                        style = MaterialTheme.typography.labelSmall,
                        // Color contrastado basado en surface del tema
                        color = MaterialTheme.colorScheme.surface,
                        // Texto en negrita
                        fontWeight = FontWeight.Bold,
                        // Relleno interior horizontal de 8 dp y vertical de 4 dp
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Botón circular flotante en la esquina superior derecha para alternar favorito
                IconButton(
                    // Llama a onToggleFavorito pasando el id del lugar
                    onClick = { data.onToggleFavorito(lugar.id) },
                    // Posiciona, recorta en círculo y da fondo claro semitransparente
                    modifier = Modifier
                        // Alineación superior derecha
                        .align(Alignment.TopEnd)
                        // Margen exterior de 8 dp
                        .padding(8.dp)
                        // Recorte circular
                        .clip(CircleShape)
                        // Fondo claro semitransparente del tema
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
                        // Tamaño total del botón de 36 dp
                        .size(36.dp)
                ) {
                    // Icono de corazón que refleja si el lugar está marcado como favorito
                    Icon(
                        // Corazón relleno si esFavorito es true, borde si es false
                        imageVector = if (lugar.esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        // Texto de accesibilidad obtenido desde strings.xml
                        contentDescription = stringResource(R.string.cd_favorito),
                        // Color primario si es favorito o variante de superficie si no lo es
                        tint = if (lugar.esFavorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        // Tamaño del icono de 20 dp
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Columna inferior con ubicación, valoración, título, descripción y botón de acción
            Column(
                // Ocupa todo el ancho y aplica relleno interior de 16 dp
                modifier = Modifier
                    // Ancho completo
                    .fillMaxWidth()
                    // Margen interior de 16 dp en todos los lados
                    .padding(16.dp)
            ) {
                // Fila superior que separa ubicación a la izquierda y valoración a la derecha
                Row(
                    // Ancho completo
                    modifier = Modifier.fillMaxWidth(),
                    // Separa los elementos a ambos extremos
                    horizontalArrangement = Arrangement.SpaceBetween,
                    // Centra verticalmente los elementos de la fila
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Fila interna para el icono y texto de ubicación
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Icono de ubicación
                        Icon(
                            // Vector de pin de ubicación
                            imageVector = Icons.Default.LocationOn,
                            // Descripción de accesibilidad desde strings.xml
                            contentDescription = stringResource(R.string.cd_ubicacion),
                            // Color primario del tema
                            tint = MaterialTheme.colorScheme.primary,
                            // Tamaño de 16 dp
                            modifier = Modifier.size(16.dp)
                        )
                        // Separación horizontal de 4 dp entre icono y texto
                        Spacer(modifier = Modifier.width(4.dp))
                        // Texto con la ubicación del lugar
                        Text(
                            // Nombre de la ubicación
                            text = lugar.ubicacion,
                            // Estilo de texto pequeño
                            style = MaterialTheme.typography.bodySmall,
                            // Color secundario de texto
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Fila interna para el icono de estrella y la puntuación
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Icono de estrella
                        Icon(
                            // Vector de estrella
                            imageVector = Icons.Default.Star,
                            // Descripción de accesibilidad desde strings.xml
                            contentDescription = stringResource(R.string.cd_valoracion),
                            // Color primario del tema
                            tint = MaterialTheme.colorScheme.primary,
                            // Tamaño de 16 dp
                            modifier = Modifier.size(16.dp)
                        )
                        // Separación horizontal de 4 dp entre estrella y puntuación
                        Spacer(modifier = Modifier.width(4.dp))
                        // Texto con valoración numérica y número de reseñas
                        Text(
                            // Formatea valoración y número de reseñas
                            text = "${lugar.valoracion} (${lugar.numResenas})",
                            // Estilo de texto pequeño
                            style = MaterialTheme.typography.bodySmall,
                            // Grosor seminegrita
                            fontWeight = FontWeight.SemiBold,
                            // Color principal sobre superficie
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Espacio vertical de 8 dp tras la fila de ubicación y valoración
                Spacer(modifier = Modifier.height(8.dp))

                // Título principal del lugar
                Text(
                    // Título obtenido del modelo Lugar
                    text = lugar.titulo,
                    // Estilo de título grande del tema
                    style = MaterialTheme.typography.titleLarge,
                    // Título en negrita
                    fontWeight = FontWeight.Bold,
                    // Color principal sobre superficie
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Espacio vertical de 4 dp entre título y descripción corta
                Spacer(modifier = Modifier.height(4.dp))

                // Descripción breve del lugar limitada a dos líneas
                Text(
                    // Texto de descripción corta
                    text = lugar.descripcionCorta,
                    // Estilo de cuerpo mediano
                    style = MaterialTheme.typography.bodyMedium,
                    // Color secundario sobre superficie
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    // Máximo de 2 líneas visibles
                    maxLines = 2,
                    // Muestra puntos suspensivos si se corta el texto
                    overflow = TextOverflow.Ellipsis
                )

                // Espacio vertical de 12 dp antes del botón inferior
                Spacer(modifier = Modifier.height(12.dp))

                // Fila inferior para alinear el botón "Ver más" a la derecha
                Row(
                    // Ancho completo
                    modifier = Modifier.fillMaxWidth(),
                    // Alinea el contenido al extremo derecho
                    horizontalArrangement = Arrangement.End
                ) {
                    // Botón para navegar al detalle del lugar
                    Button(
                        // Dispara onVerMasClick pasando el id del lugar
                        onClick = { data.onVerMasClick(lugar.id) },
                        // Fondo con el color primario del tema
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        // Esquinas redondeadas de 8 dp
                        shape = RoundedCornerShape(8.dp),
                        // Relleno interior horizontal de 16 dp y vertical de 8 dp
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        // Texto del botón obtenido desde strings.xml
                        Text(
                            // Recurso de texto "Ver más"
                            text = stringResource(R.string.accion_ver_mas),
                            // Estilo de etiqueta grande
                            style = MaterialTheme.typography.labelLarge,
                            // Grosor tipográfico medio
                            fontWeight = FontWeight.Medium
                        )
                        // Espacio horizontal de 6 dp entre texto e icono
                        Spacer(modifier = Modifier.width(6.dp))
                        // Icono de flecha hacia la derecha acompañando al texto
                        Icon(
                            // Flecha adaptada a dirección de lectura
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            // Descripción de accesibilidad desde strings.xml
                            contentDescription = stringResource(R.string.cd_flecha_derecha),
                            // Tamaño del icono de 16 dp
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
// Crea el ejemplo de La Valeta del mockup para previsualizar TarjetaLugar
private fun laValetaTarjetaLugarPreviewSample(): Lugar = Lugar(
    // Identificador del lugar
    id = "la-valeta",
    // Nombre principal del lugar según el mockup
    titulo = "La Valeta (Valletta)",
    // Categoría turística del mockup
    categoria = "Historia",
    // Ubicación dentro del archipiélago según el mockup
    ubicacion = "Isla de Malta",
    // Valoración media de ejemplo según el mockup
    valoracion = 4.9,
    // Cantidad de reseñas en formato texto según el mockup
    numResenas = "1.8k",
    // Descripción corta para la tarjeta
    descripcionCorta = "La majestuosa capital amurallada barroca de los caballeros con vistas al puerto.",
    // Descripción detallada del lugar
    descripcionCompleta = "Capital histórica y barroca de Malta, declarada Patrimonio de la Humanidad, con calles escalonadas, baluartes y miradores.",
    // URL principal de ejemplo
    imagenUrl = "https://example.com/la-valeta.jpg",
    // Lista de imágenes para galería
    fotosGaleria = listOf(
        "https://example.com/la-valeta-1.jpg",
        "https://example.com/la-valeta-2.jpg"
    ),
    // Estado inicial de favorito según el mockup
    esFavorito = false,
    // Condiciones climáticas de ejemplo
    condicionesHoy = CondicionesClima(
        estado = "Soleado",
        agua = "Mar calmado",
        oleaje = "Brisa suave",
        temperatura = "25 °C",
        uv = "UV alto"
    ),
    // Lista vacía de servicios para la vista previa de la tarjeta
    servicios = emptyList()
)

// Preview de TarjetaLugar con La Valeta según el mockup (C5)
@Preview(showBackground = true)
@Composable
private fun TarjetaLugarPreview() {
    // Aplica el tema para la previsualización
    MaterialTheme {
        // Renderiza TarjetaLugar con La Valeta
        TarjetaLugar(
            data = TarjetaLugarData(
                lugar = laValetaTarjetaLugarPreviewSample()
            )
        )
    }
}
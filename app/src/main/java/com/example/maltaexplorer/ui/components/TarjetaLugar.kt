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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.maltaexplorer.R
import com.example.maltaexplorer.model.Lugar

// Declara la función Composable reutilizable que pinta la tarjeta de un lugar turístico
@Composable
fun TarjetaLugar(
    // Recibe el objeto Lugar con todos sus datos (título, foto, valoración, etc.)
    lugar: Lugar,
    // Callback que se ejecuta al pulsar la tarjeta o "Ver más", pasando el id del lugar
    onVerMasClick: (String) -> Unit,
    // Callback que se ejecuta al pulsar el corazón, pasando el id del lugar
    onToggleFavorito: (String) -> Unit,
    // Modificador opcional externo para personalizar tamaño o márgenes desde quien llame a la tarjeta
    modifier: Modifier = Modifier
) {
    // Contenedor principal con elevación y bordes redondeados tipo tarjeta de Material 3
    Card(
        // Aplica el modifier recibido, ocupa todo el ancho disponible y hace toda la tarjeta clicable
        modifier = modifier
            .fillMaxWidth()
            .clickable { onVerMasClick(lugar.id) },
        // Redondea las 4 esquinas de la tarjeta con un radio de 16 dp
        shape = RoundedCornerShape(16.dp),
        // Añade una sombra suave de 4 dp para dar sensación de relieve
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        // Usa el color de superficie definido en el tema actual de Material
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        // Organiza verticalmente la zona superior (imagen) y la zona inferior (textos y botón)
        Column(modifier = Modifier.fillMaxWidth()) {

            // Contenedor que permite superponer etiquetas y botones encima de la foto principal
            Box(
                // Ocupa todo el ancho de la tarjeta con una altura fija de 200 dp
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                // Carga de forma asíncrona la fotografía del lugar desde su URL
                AsyncImage(
                    // Indica la URL de la imagen obtenida del objeto lugar
                    model = lugar.imagenUrl,
                    // Usa el título dinámico del lugar como descripción accesible de la foto
                    contentDescription = lugar.titulo,
                    // Recorta la imagen manteniendo su proporción para rellenar todo el hueco sin deformarse
                    contentScale = ContentScale.Crop,
                    // Hace que la imagen ocupe el 100% del ancho y alto del Box
                    modifier = Modifier.fillMaxSize()
                )

                // Etiqueta flotante con fondo oscuro del tema para mostrar la categoría
                Surface(
                    // Redondea las esquinas de la etiqueta a 8 dp
                    shape = RoundedCornerShape(8.dp),
                    // Pinta fondo oscuro semitransparente basado en onSurface del tema
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                    // Coloca la etiqueta en la esquina superior izquierda con 12 dp de margen
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                ) {
                    // Muestra el nombre de la categoría en letras mayúsculas
                    Text(
                        // Convierte la categoría del lugar a mayúsculas
                        text = lugar.categoria.uppercase(),
                        // Aplica el estilo tipográfico de etiqueta pequeña del tema
                        style = MaterialTheme.typography.labelSmall,
                        // Pinta el texto con el color de superficie del tema para contrastar
                        color = MaterialTheme.colorScheme.surface,
                        // Pone el texto en negrita
                        fontWeight = FontWeight.Bold,
                        // Añade margen interior horizontal de 8 dp y vertical de 4 dp dentro de la etiqueta
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Botón circular flotante en la esquina superior derecha para marcar o desmarcar favorito
                IconButton(
                    // Ejecuta el evento onToggleFavorito enviando el identificador del lugar
                    onClick = { onToggleFavorito(lugar.id) },
                    // Posiciona y da forma circular con fondo claro del tema al botón
                    modifier = Modifier
                        // Lo alinea en la esquina superior derecha del Box
                        .align(Alignment.TopEnd)
                        // Separa el botón 8 dp de los bordes superior y derecho
                        .padding(8.dp)
                        // Recorta el área del botón en forma de círculo
                        .clip(CircleShape)
                        // Pinta un fondo claro semitransparente basado en surface del tema
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.85f))
                        // Fija el tamaño del botón en 36x36 dp
                        .size(36.dp)
                ) {
                    // Dibuja el icono del corazón dentro del botón circular
                    Icon(
                        // Elige corazón relleno si esFavorito es true, o solo el contorno si es false
                        imageVector = if (lugar.esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        // Lee el texto "Favorito" desde strings.xml para accesibilidad
                        contentDescription = stringResource(R.string.cd_favorito),
                        // Pinta el corazón usando colores del tema según el estado de favorito
                        tint = if (lugar.esFavorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        // Ajusta el tamaño del icono interior a 20x20 dp
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Columna inferior que contiene los datos textuales y el botón de acción de la tarjeta
            Column(
                // Ocupa todo el ancho y añade un margen interior de 16 dp en los cuatro lados
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Fila superior que reparte la ubicación a la izquierda y la valoración a la derecha
                Row(
                    // Ocupa todo el ancho disponible de la columna
                    modifier = Modifier.fillMaxWidth(),
                    // Empuja un bloque al extremo izquierdo y el otro al extremo derecho
                    horizontalArrangement = Arrangement.SpaceBetween,
                    // Centra verticalmente ambos bloques en la misma línea
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Sub-fila que agrupa el icono de pin y el texto de la ubicación
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Dibuja el icono de pin de ubicación
                        Icon(
                            // Selecciona el vector estándar de pin de mapa
                            imageVector = Icons.Default.LocationOn,
                            // Lee la descripción "Ubicación" desde strings.xml
                            contentDescription = stringResource(R.string.cd_ubicacion),
                            // Pinta el icono con el color primario del tema
                            tint = MaterialTheme.colorScheme.primary,
                            // Fija el tamaño del icono en 16x16 dp
                            modifier = Modifier.size(16.dp)
                        )
                        // Inserta un espacio horizontal de 4 dp entre el pin y el texto
                        Spacer(modifier = Modifier.width(4.dp))
                        // Muestra el nombre de la ubicación del lugar
                        Text(
                            // Pasa el texto dinámico de ubicación del objeto lugar
                            text = lugar.ubicacion,
                            // Usa el estilo de texto pequeño del tema
                            style = MaterialTheme.typography.bodySmall,
                            // Usa un tono secundario de texto para diferenciarlo del título principal
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Sub-fila que agrupa el icono de estrella y la puntuación numérica
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Dibuja el icono de estrella
                        Icon(
                            // Selecciona el vector estándar de estrella rellena
                            imageVector = Icons.Default.Star,
                            // Lee la descripción "Valoración" desde strings.xml
                            contentDescription = stringResource(R.string.cd_valoracion),
                            // Pinta la estrella con el color primario del tema
                            tint = MaterialTheme.colorScheme.primary,
                            // Fija el tamaño de la estrella en 16x16 dp
                            modifier = Modifier.size(16.dp)
                        )
                        // Inserta un espacio horizontal de 4 dp entre la estrella y la nota
                        Spacer(modifier = Modifier.width(4.dp))
                        // Muestra la puntuación junto al número de reseñas entre paréntesis
                        Text(
                            // Combina la valoración numérica y el contador de reseñas del lugar
                            text = "${lugar.valoracion} (${lugar.numResenas})",
                            // Aplica el estilo de texto pequeño del tema
                            style = MaterialTheme.typography.bodySmall,
                            // Aplica grosor seminegrita para destacar la puntuación
                            fontWeight = FontWeight.SemiBold,
                            // Usa el color principal de texto sobre superficie
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Añade una separación vertical de 8 dp debajo de la fila de ubicación/valoración
                Spacer(modifier = Modifier.height(8.dp))

                // Muestra el título principal del lugar
                Text(
                    // Pasa el título dinámico del objeto lugar
                    text = lugar.titulo,
                    // Aplica el estilo de título grande del tema
                    style = MaterialTheme.typography.titleLarge,
                    // Resalta el título en negrita
                    fontWeight = FontWeight.Bold,
                    // Usa el color principal de texto sobre superficie
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Añade una separación vertical de 4 dp entre el título y la descripción corta
                Spacer(modifier = Modifier.height(4.dp))

                // Muestra un resumen breve del lugar limitado a 2 líneas
                Text(
                    // Pasa la descripción corta del objeto lugar
                    text = lugar.descripcionCorta,
                    // Usa el estilo de cuerpo de texto mediano del tema
                    style = MaterialTheme.typography.bodyMedium,
                    // Usa un color más suave para jerarquizar respecto al título
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    // Limita el texto a un máximo de 2 líneas visibles
                    maxLines = 2,
                    // Añade "..." al final si el texto supera las 2 líneas
                    overflow = TextOverflow.Ellipsis
                )

                // Añade una separación vertical de 12 dp antes del botón inferior
                Spacer(modifier = Modifier.height(12.dp))

                // Fila inferior usada para alinear el botón "Ver más" a la derecha
                Row(
                    // Ocupa todo el ancho disponible de la tarjeta
                    modifier = Modifier.fillMaxWidth(),
                    // Empuja el contenido hijo hacia el borde derecho
                    horizontalArrangement = Arrangement.End
                ) {
                    // Botón principal de acción para abrir el detalle del lugar
                    Button(
                        // Llama a onVerMasClick pasando el id del lugar pulsado
                        onClick = { onVerMasClick(lugar.id) },
                        // Usa el color primario del tema como fondo del botón
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        // Redondea las esquinas del botón a 8 dp
                        shape = RoundedCornerShape(8.dp),
                        // Define el relleno interior del botón (16 dp a los lados, 8 dp arriba/abajo)
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        // Texto visible dentro del botón
                        Text(
                            // Lee el texto "Ver más" desde strings.xml en lugar de escribirlo fijo
                            text = stringResource(R.string.accion_ver_mas),
                            // Aplica el estilo tipográfico de botón/etiqueta grande
                            style = MaterialTheme.typography.labelLarge,
                            // Aplica grosor medio al texto del botón
                            fontWeight = FontWeight.Medium
                        )
                        // Inserta un espacio horizontal de 6 dp entre el texto y la flecha
                        Spacer(modifier = Modifier.width(6.dp))
                        // Dibuja el icono de flecha apuntando hacia la derecha
                        Icon(
                            // Usa la flecha auto-espejada para respetar la dirección de lectura
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            // Lee la descripción "Flecha derecha" desde strings.xml
                            contentDescription = stringResource(R.string.cd_flecha_derecha),
                            // Ajusta el tamaño del icono de flecha a 16x16 dp
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
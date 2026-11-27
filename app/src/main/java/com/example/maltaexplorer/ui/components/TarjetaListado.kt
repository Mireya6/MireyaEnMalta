package com.example.maltaexplorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.maltaexplorer.R
import com.example.maltaexplorer.model.Lugar


// Declara el composable que muestra la información textual y el botón inferior de la tarjeta
@Composable
fun ContenidoTarjeta(
    // Texto de ubicación del lugar (por ejemplo, isla o zona)
    ubicacion: String,
    // Texto de valoración del lugar (por ejemplo, puntuación numérica)
    valoracion: String,
    // Título principal del lugar
    titulo: String,
    // Descripción breve del lugar
    descripcion: String,
    // Acción que se ejecuta al pulsar el botón para ver más detalles
    onVerMasClick: () -> Unit,
    // Modificador opcional recibido desde el componente padre para aplicar en la raíz
    modifier: Modifier = Modifier
) {
    // Columna principal que organiza los elementos de arriba abajo con margen y separación vertical
    Column(
        // Aplica el modifier recibido en la raíz, ocupa el ancho total y añade 12 dp de padding
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        // Separa verticalmente cada elemento hijo en 6 dp
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Fila superior que coloca la ubicación a la izquierda y la valoración a la derecha
        Row(
            // Ocupa todo el ancho de la columna
            modifier = Modifier.fillMaxWidth(),
            // Coloca un elemento pegado a la izquierda y otro a la derecha
            horizontalArrangement = Arrangement.SpaceBetween,
            // Centra verticalmente ambos elementos en la misma línea
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Subfila que agrupa el icono de ubicación y el texto de ubicación
            Row(
                // Centra verticalmente el icono y el texto
                verticalAlignment = Alignment.CenterVertically,
                // Deja 4 dp de separación horizontal entre el icono y el texto
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Dibuja el icono de pin de ubicación
                Icon(
                    // Usa el icono estándar de ubicación en el mapa
                    imageVector = Icons.Default.LocationOn,
                    // Obtiene la descripción accesible desde strings.xml
                    contentDescription = stringResource(R.string.cd_pin_ubicacion),
                    // Define el tamaño del icono en 16 dp
                    modifier = Modifier.size(16.dp),
                    // Aplica el color secundario de texto e iconos sobre superficie del tema
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // Muestra el texto de ubicación recibido por parámetro
                Text(
                    // Asigna el valor dinámico de ubicación
                    text = ubicacion,
                    // Aplica el estilo tipográfico de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Usa el color secundario para suavizar la jerarquía visual
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Subfila que agrupa el icono de estrella y el texto de valoración
            Row(
                // Centra verticalmente la estrella y la valoración
                verticalAlignment = Alignment.CenterVertically,
                // Deja 4 dp de separación horizontal entre la estrella y el texto
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Dibuja el icono de estrella para la valoración
                Icon(
                    // Usa el icono estándar de estrella rellena
                    imageVector = Icons.Default.Star,
                    // Obtiene la descripción accesible desde strings.xml
                    contentDescription = stringResource(R.string.cd_estrella),
                    // Define el tamaño del icono en 16 dp
                    modifier = Modifier.size(16.dp),
                    // Usa el color primario del tema para destacar la valoración
                    tint = MaterialTheme.colorScheme.primary
                )
                // Muestra el texto de valoración recibido por parámetro
                Text(
                    // Asigna el valor dinámico de valoración
                    text = valoracion,
                    // Aplica el estilo tipográfico de etiqueta mediana del tema
                    style = MaterialTheme.typography.labelMedium,
                    // Pone el texto en negrita para resaltarlo
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Muestra el título principal del lugar
        Text(
            // Asigna el valor dinámico del título
            text = titulo,
            // Aplica el estilo tipográfico de título mediano del tema
            style = MaterialTheme.typography.titleMedium,
            // Pone el título en negrita
            fontWeight = FontWeight.Bold,
            // Limita el título a una sola línea
            maxLines = 1,
            // Añade puntos suspensivos si el título no cabe en una línea
            overflow = TextOverflow.Ellipsis
        )

        // Muestra la descripción corta del lugar
        Text(
            // Asigna la descripción corta recibida por parámetro
            text = descripcion,
            // Aplica el estilo tipográfico de cuerpo pequeño del tema
            style = MaterialTheme.typography.bodySmall,
            // Usa el color secundario de superficie para diferenciarlo del título
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            // Limita la descripción a un máximo de 2 líneas
            maxLines = 2,
            // Añade puntos suspensivos si supera las 2 líneas
            overflow = TextOverflow.Ellipsis
        )

        // Botón alineado a la derecha para abrir más información del lugar
        Button(
            // Ejecuta la función onVerMasClick al pulsar el botón
            onClick = onVerMasClick,
            // Coloca el botón al final horizontal dentro de la columna
            modifier = Modifier.align(Alignment.End),
            // Ajusta el relleno interior del botón (12 dp horizontal y 6 dp vertical)
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
        ) {
            // Fila interna del botón para colocar el texto y la flecha uno al lado del otro
            Row(
                // Centra verticalmente el texto y el icono de flecha
                verticalAlignment = Alignment.CenterVertically,
                // Separa el texto y la flecha 4 dp en horizontal
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Lee el texto fijo del botón desde strings.xml
                Text(text = stringResource(R.string.accion_ver_mas))
                // Dibuja el icono de flecha apuntando hacia la derecha
                Icon(
                    // Usa la flecha adaptada a la dirección de lectura
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    // Obtiene la descripción accesible de la flecha desde strings.xml
                    contentDescription = stringResource(R.string.cd_flecha_derecha),
                    // Define el tamaño del icono en 16 dp
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

// Declara la tarjeta completa para representar un elemento Lugar dentro de un listado
@Composable
fun TarjetaListado(
    // Modelo de datos del lugar a mostrar en la tarjeta
    lugar: Lugar,
    // Acción que se ejecuta al pulsar ver más
    onVerMasClick: () -> Unit,
    // Acción que se ejecuta al cambiar el estado de favorito
    onToggleFavorito: () -> Unit,
    // Modificador opcional aplicado en la raíz de la tarjeta
    modifier: Modifier = Modifier
) {
    // Contenedor Card con esquinas redondeadas y elevación suave
    Card(
        // Aplica el modifier recibido en la raíz y ocupa el ancho disponible
        modifier = modifier.fillMaxWidth(),
        // Redondea las esquinas de la tarjeta en 12 dp
        shape = RoundedCornerShape(12.dp),
        // Configura la elevación por defecto de la tarjeta en 2 dp
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // Columna que agrupa el botón superior de favorito y el contenido del lugar
        Column(modifier = Modifier.fillMaxWidth()) {
            // Fila superior alineada a la derecha para el botón de favorito
            Row(
                // Ocupa todo el ancho y aplica márgenes horizontales y verticales
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                // Coloca el botón de favorito a la derecha
                horizontalArrangement = Arrangement.End
            ) {
                // Botón de icono para alternar si el lugar está marcado como favorito
                IconButton(onClick = onToggleFavorito) {
                    // Icono de corazón relleno si es favorito o borde si no lo es
                    Icon(
                        // Selecciona el icono según lugar.esFavorito
                        imageVector = if (lugar.esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        // Obtiene la descripción accesible de favorito desde strings.xml
                        contentDescription = stringResource(R.string.cd_favorito),
                        // Color primario si es favorito o secundario sobre superficie
                                tint = if (lugar.esFavorito) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )

                }
            }

            // Pinta el contenido principal de la tarjeta con los datos del lugar
            ContenidoTarjeta(
                // Pasa la ubicación del modelo Lugar
                ubicacion = lugar.ubicacion,
                // Convierte la valoración a texto para mostrarla
                valoracion = lugar.valoracion.toString(),
                // Pasa el título del modelo Lugar
                titulo = lugar.titulo,
                // Pasa la descripción corta del modelo Lugar
                descripcion = lugar.descripcionCorta,
                // Reenvía el callback al pulsar ver más
                onVerMasClick = onVerMasClick
            )
        }
    }
}
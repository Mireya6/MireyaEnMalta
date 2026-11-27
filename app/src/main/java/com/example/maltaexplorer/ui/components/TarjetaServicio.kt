package com.example.maltaexplorer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.maltaexplorer.model.Servicio
import com.example.maltaexplorer.model.TipoServicioIcono

// Data class que agrupa los datos de TarjetaServicio (C1)
data class TarjetaServicioData(
    // Objeto Servicio con su tipo de icono, título y descripción
    val servicio: Servicio
)

// Declara el componente Composable reutilizable que muestra un servicio disponible del lugar
@Composable
fun TarjetaServicio(
    // Datos encapsulados en TarjetaServicioData
    data: TarjetaServicioData,
    // Modificador opcional externo para personalizar tamaño o márgenes desde quien use la tarjeta
    modifier: Modifier = Modifier
) {
    // Extrae el objeto Servicio desde data para un acceso más limpio
    val servicio = data.servicio

    // Contenedor tipo tarjeta con ancho fijo, esquinas redondeadas, color de superficie y sombra suave
    Card(
        // Fija el ancho de la tarjeta en 170 dp y aplica el modifier externo si se pasa
        modifier = modifier.width(170.dp),
        // Redondea las esquinas de la tarjeta con un radio de 10 dp
        shape = RoundedCornerShape(10.dp),
        // Usa el color de superficie del tema actual de Material 3
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Aplica una elevación de 2 dp para generar una sombra ligera
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // Fila principal con margen interior de 10 dp y elementos centrados verticalmente
        Row(
            // Añade 10 dp de relleno interior en todos los lados dentro de la tarjeta
            modifier = Modifier.padding(10.dp),
            // Centra verticalmente el bloque del icono y la columna de textos
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contenedor cuadrado que actúa como fondo del icono del servicio
            Box(
                // Fija un tamaño de 36x36 dp, recorta esquinas a 8 dp y pinta fondo primaryContainer
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                // Centra el icono tanto horizontal como verticalmente dentro del Box
                contentAlignment = Alignment.Center
            ) {
                // Selecciona el icono vectorial correspondiente al tipo de servicio
                val iconVector = when (servicio.tipoIcono) {
                    // Icono de barco para servicios marítimos o traslados en barco
                    TipoServicioIcono.BARCO -> Icons.Filled.DirectionsBoat
                    // Icono de piscina/agua para actividades de snorkel o baño
                    TipoServicioIcono.SNORKEL -> Icons.Filled.Pool
                    // Icono de autobús para transporte público o bus
                    TipoServicioIcono.BUS -> Icons.Filled.DirectionsBus
                    // Icono de aparcamiento para zonas de parking
                    TipoServicioIcono.PARKING -> Icons.Filled.LocalParking
                }
                // Dibuja el icono del servicio dentro del recuadro redondeado
                Icon(
                    // Asigna el vector elegido en el when anterior
                    imageVector = iconVector,
                    // Se deja null porque el texto de título y descripción ya describe el servicio
                    contentDescription = null,
                    // Usa el color de contenido sobre primaryContainer para buen contraste
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    // Ajusta el tamaño interior del icono a 20x20 dp
                    modifier = Modifier.size(20.dp)
                )
            }

            // Añade una separación horizontal de 8 dp entre el recuadro del icono y los textos
            Spacer(modifier = Modifier.width(8.dp))

            // Columna que apila el título del servicio encima de su descripción breve
            Column {
                // Muestra el nombre corto del servicio
                Text(
                    // Lee el título dinámico desde el objeto servicio
                    text = servicio.titulo,
                    // Aplica el estilo de etiqueta mediana del tema
                    style = MaterialTheme.typography.labelMedium,
                    // Resalta el título en negrita
                    fontWeight = FontWeight.Bold,
                    // Limita el título a una sola línea
                    maxLines = 1,
                    // Corta con puntos suspensivos (...) si no cabe en una línea
                    overflow = TextOverflow.Ellipsis
                )
                // Muestra la descripción corta del servicio
                Text(
                    // Lee la descripción dinámica desde el objeto servicio
                    text = servicio.descripcion,
                    // Aplica el estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Usa un color secundario para diferenciar la descripción del título
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    // Limita la descripción a una sola línea
                    maxLines = 1,
                    // Corta con puntos suspensivos (...) si supera el ancho disponible
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
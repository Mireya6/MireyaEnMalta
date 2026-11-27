
package com.example.maltaexplorer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.maltaexplorer.model.Servicio
import com.example.maltaexplorer.model.TipoServicioIcono
import com.example.maltaexplorer.ui.components.TarjetaServicio
import com.example.maltaexplorer.ui.components.TarjetaServicioData

// Data class que agrupa los datos y callbacks de PantallaDetalleLugar (C1)
data class PantallaDetalleLugarData(
    // Objeto Lugar con la información completa del lugar turístico
    val lugar: Lugar,
    // Callback ejecutado al pulsar el botón de volver atrás
    val onVolverClick: () -> Unit = {},
    // Callback ejecutado al pulsar el botón de compartir
    val onCompartirClick: () -> Unit = {},
    // Callback ejecutado al alternar el estado de favorito
    val onToggleFavorito: () -> Unit = {},
    // Callback ejecutado al pulsar "Ver en mapa"
    val onVerEnMapaClick: () -> Unit = {}
)

// Data class que agrupa los datos y callbacks de CabeceraDetalle (C1)
data class CabeceraDetalleData(
    // Indica si el lugar está marcado como favorito
    val esFavorito: Boolean,
    // Callback ejecutado al pulsar volver
    val onVolver: () -> Unit = {},
    // Callback ejecutado al pulsar compartir
    val onCompartir: () -> Unit = {},
    // Callback ejecutado al pulsar favorito
    val onFavorito: () -> Unit = {}
)

// Data class que agrupa los datos de GaleriaLugar (C1 y C4: sin contadores fijos en el composable)
data class GaleriaLugarData(
    // URL de la fotografía principal
    val imagenPrincipal: String,
    // Lista de URLs de fotos secundarias de la galería
    val imagenesGaleria: List<String>,
    // Categoría del lugar
    val categoria: String,
    // Número total de fotos a mostrar en la etiqueta inferior derecha
    val totalFotos: Int = 0,
    // Cantidad de fotos restantes tras mostrar las primeras miniaturas
    val fotosRestantes: Int = 0
)

// Data class que agrupa los datos y callbacks de IdentidadLugar (C1 y C4: etiqueta dinámica)
data class IdentidadLugarData(
    // Objeto Lugar con categoría, título y ubicación
    val lugar: Lugar,
    // Texto destacado opcional (si es nulo o vacío, no se muestra el distintivo)
    val etiquetaDestacado: String? = null,
    // Callback ejecutado al pulsar "Ver en mapa"
    val onVerEnMapaClick: () -> Unit = {}
)

// Data class que agrupa los datos y callbacks de ResumenValoracion (C1 y C4: etiqueta dinámica)
data class ResumenValoracionData(
    // Puntuación numérica del lugar
    val valoracion: Double,
    // Etiqueta descriptiva de la valoración (por ejemplo, "Excelente")
    val etiquetaValoracion: String = "",
    // Número de reseñas en formato texto
    val numResenas: String,
    // Callback ejecutado al pulsar el botón de opiniones
    val onOpinionesClick: () -> Unit = {}
)

// Data class que agrupa los datos de CondicionesHoy (C1)
data class CondicionesHoyData(
    // Objeto con las condiciones climáticas y del entorno del día
    val condiciones: CondicionesClima
)

// Data class que agrupa los datos de DescripcionLugar (C1)
data class DescripcionLugarData(
    // Texto descriptivo completo del lugar
    val descripcion: String
)

// Data class que agrupa los datos de ServiciosLugar (C1)
data class ServiciosLugarData(
    // Lista de servicios disponibles en el lugar
    val servicios: List<Servicio>
)

// Composable principal de la pantalla de detalle de un lugar turístico
@Composable
fun PantallaDetalleLugar(
    // Datos y callbacks encapsulados en PantallaDetalleLugarData
    data: PantallaDetalleLugarData,
    // Modificador opcional externo aplicado en la raíz del composable (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Extrae el objeto Lugar desde data para un acceso más limpio
    val lugar = data.lugar
    // Crea y recuerda el estado de desplazamiento vertical de la pantalla
    val scrollState = rememberScrollState()

    // Columna principal desplazable que contiene todas las secciones del detalle
    Column(
        // Aplica el modifier externo, ocupa toda la pantalla, pinta fondo y habilita scroll
        modifier = modifier
            // Ocupa todo el ancho y alto disponible
            .fillMaxSize()
            // Usa el color de fondo principal del tema
            .background(MaterialTheme.colorScheme.background)
            // Permite desplazar verticalmente el contenido
            .verticalScroll(scrollState)
    ) {
        // Cabecera superior con botón de volver, título y acciones de favorito/compartir
        CabeceraDetalle(
            // Pasa el estado de favorito y los callbacks en CabeceraDetalleData
            data = CabeceraDetalleData(
                // Estado actual de favorito del lugar
                esFavorito = lugar.esFavorito,
                // Acción al pulsar volver
                onVolver = data.onVolverClick,
                // Acción al pulsar compartir
                onCompartir = data.onCompartirClick,
                // Acción al pulsar favorito
                onFavorito = data.onToggleFavorito
            )
        )

        // Galería de imágenes con foto principal, contadores dinámicos y miniaturas
        GaleriaLugar(
            // Pasa datos de galería y contadores calculados desde lugar
            data = GaleriaLugarData(
                // URL de la fotografía principal
                imagenPrincipal = lugar.imagenUrl,
                // Lista de fotos secundarias de la galería
                imagenesGaleria = lugar.fotosGaleria,
                // Categoría del lugar
                categoria = lugar.categoria,
                // Total de fotos (foto principal + fotos de la galería)
                totalFotos = lugar.fotosGaleria.size + 1,
                // Fotos restantes tras mostrar las 3 primeras miniaturas
                fotosRestantes = (lugar.fotosGaleria.size - 3).coerceAtLeast(0)
            )
        )

        // Separación vertical de 16 dp entre galería y bloque de identidad
        Spacer(modifier = Modifier.height(16.dp))

        // Bloque de identidad con categoría, distintivo opcional, título, ubicación y mapa
        IdentidadLugar(
            // Pasa lugar, etiqueta destacada dinámica y callback del mapa
            data = IdentidadLugarData(
                // Objeto Lugar completo
                lugar = lugar,
                // Muestra la etiqueta solo si la valoración supera el umbral indicado
                etiquetaDestacado = if (lugar.valoracion >= 4.5) "Destino imprescindible" else null,
                // Callback para abrir en el mapa
                onVerEnMapaClick = data.onVerEnMapaClick
            ),
            // Margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Separación vertical de 16 dp entre identidad y resumen de valoración
        Spacer(modifier = Modifier.height(16.dp))

        // Calcula de forma dinámica la etiqueta descriptiva según la valoración del lugar
        val etiquetaValoracion = when {
            // Valoración muy alta
            lugar.valoracion >= 4.5 -> "Excelente"
            // Valoración alta
            lugar.valoracion >= 4.0 -> "Muy bueno"
            // Valoración media-alta
            lugar.valoracion >= 3.0 -> "Bueno"
            // Resto de puntuaciones
            else -> "Aceptable"
        }

        // Tarjeta con nota numérica, etiqueta dinámica, número de reseñas y botón de opiniones
        ResumenValoracion(
            // Pasa puntuación, etiqueta calculada y número de reseñas
            data = ResumenValoracionData(
                // Puntuación numérica del lugar
                valoracion = lugar.valoracion,
                // Etiqueta de valoración dinámica
                etiquetaValoracion = etiquetaValoracion,
                // Número de reseñas en formato texto
                numResenas = lugar.numResenas
            ),
            // Margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Separación vertical de 16 dp entre valoración y condiciones del día
        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta con las condiciones climáticas y del entorno de hoy
        CondicionesHoy(
            // Pasa el objeto de condiciones climáticas del lugar
            data = CondicionesHoyData(
                // Condiciones climáticas actuales
                condiciones = lugar.condicionesHoy
            ),
            // Margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Separación vertical de 16 dp entre condiciones y descripción
        Spacer(modifier = Modifier.height(16.dp))

        // Sección de descripción expandible ("Leer más / Leer menos")
        DescripcionLugar(
            // Pasa la descripción completa del lugar
            data = DescripcionLugarData(
                // Texto descriptivo completo
                descripcion = lugar.descripcionCompleta
            ),
            // Margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Separación vertical de 20 dp entre descripción y servicios
        Spacer(modifier = Modifier.height(20.dp))

        // Lista horizontal de servicios disponibles y contador de puntos
        ServiciosLugar(
            // Pasa la lista de servicios del lugar
            data = ServiciosLugarData(
                // Servicios asociados al lugar
                servicios = lugar.servicios
            ),
            // Margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Espacio final inferior de 32 dp para comodidad al hacer scroll
        Spacer(modifier = Modifier.height(32.dp))
    }
}

// Subcomponente de cabecera superior de la pantalla de detalle
@Composable
fun CabeceraDetalle(
    // Datos y callbacks de la cabecera
    data: CabeceraDetalleData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Fila superior con botón atrás, título y botones de favorito y compartir
    Row(
        // Aplica modifier externo, ancho completo, margen de barra de estado y padding
        modifier = modifier
            // Ocupa todo el ancho disponible
            .fillMaxWidth()
            // Evita solaparse con la barra de estado superior
            .statusBarsPadding()
            // Relleno horizontal y vertical de 8 dp
            .padding(horizontal = 8.dp, vertical = 8.dp),
        // Centra verticalmente los elementos
        verticalAlignment = Alignment.CenterVertically,
        // Distribuye los elementos a izquierda, centro y derecha
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Botón para volver a la pantalla anterior
        IconButton(onClick = data.onVolver) {
            // Icono de flecha hacia atrás
            Icon(
                // Vector de flecha atrás adaptado a dirección de lectura
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                // Descripción accesible desde strings.xml
                contentDescription = stringResource(R.string.cd_volver)
            )
        }

        // Título central de la pantalla de detalle
        Text(
            // Texto leído desde strings.xml
            text = stringResource(R.string.detalle_titulo),
            // Estilo de título mediano del tema
            style = MaterialTheme.typography.titleMedium,
            // Texto en negrita
            fontWeight = FontWeight.Bold
        )

        // Grupo de botones de acción a la derecha (favorito y compartir)
        Row {
            // Botón para alternar favorito
            IconButton(onClick = data.onFavorito) {
                // Icono de corazón relleno o vacío según data.esFavorito
                Icon(
                    // Selecciona el vector según si es favorito
                    imageVector = if (data.esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    // Descripción accesible desde strings.xml
                    contentDescription = stringResource(R.string.cd_favorito),
                    // Color primario si está marcado o color principal de texto si no
                    tint = if (data.esFavorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
            // Botón para compartir el lugar
            IconButton(onClick = data.onCompartir) {
                // Icono de compartir
                Icon(
                    // Vector de compartir
                    imageVector = Icons.Filled.Share,
                    // Descripción accesible desde strings.xml
                    contentDescription = stringResource(R.string.cd_compartir)
                )
            }
        }
    }
}

// Subcomponente de galería con fotografía principal y fila de miniaturas
@Composable
fun GaleriaLugar(
    // Datos de la galería
    data: GaleriaLugarData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Columna principal que apila la imagen grande y las miniaturas
    Column(modifier = modifier.fillMaxWidth()) {
        // Contenedor de la fotografía principal con etiquetas superpuestas
        Box(
            // Configura ancho, alto, márgenes laterales y esquinas redondeadas
            modifier = Modifier
                // Ocupa todo el ancho disponible
                .fillMaxWidth()
                // Altura fija de 230 dp
                .height(230.dp)
                // Margen lateral de 16 dp
                .padding(horizontal = 16.dp)
                // Esquinas redondeadas de 16 dp
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Carga de forma asíncrona la imagen principal del lugar
            AsyncImage(
                // URL de la fotografía principal
                model = data.imagenPrincipal,
                // Descripción accesible desde strings.xml
                contentDescription = stringResource(R.string.cd_vista_aerea),
                // Recorta proporcionalmente para llenar el contenedor
                contentScale = ContentScale.Crop,
                // Ocupa todo el tamaño del Box
                modifier = Modifier.fillMaxSize()
            )

            // Etiqueta superior izquierda con la categoría del lugar
            Surface(
                // Fondo oscuro semitransparente derivado de onSurface
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                // Esquinas redondeadas de 6 dp
                shape = RoundedCornerShape(6.dp),
                // Posiciona arriba a la izquierda con margen de 12 dp
                modifier = Modifier
                    // Alineación superior izquierda
                    .align(Alignment.TopStart)
                    // Margen exterior de 12 dp
                    .padding(12.dp)
            ) {
                // Texto de categoría en mayúsculas
                Text(
                    // Convierte la categoría a mayúsculas
                    text = data.categoria.uppercase(),
                    // Color de superficie para buen contraste
                    color = MaterialTheme.colorScheme.surface,
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Texto en negrita
                    fontWeight = FontWeight.Bold,
                    // Relleno interior horizontal de 8 dp y vertical de 4 dp
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Etiqueta inferior derecha con el contador dinámico de fotos
            Surface(
                // Fondo oscuro semitransparente derivado de onSurface
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                // Esquinas redondeadas de 6 dp
                shape = RoundedCornerShape(6.dp),
                // Posiciona abajo a la derecha con margen de 12 dp
                modifier = Modifier
                    // Alineación inferior derecha
                    .align(Alignment.BottomEnd)
                    // Margen exterior de 12 dp
                    .padding(12.dp)
            ) {
                // Fila con icono de galería y número total de fotos
                Row(
                    // Centra verticalmente icono y texto
                    verticalAlignment = Alignment.CenterVertically,
                    // Relleno interior horizontal de 8 dp y vertical de 4 dp
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    // Icono decorativo de galería de fotos
                    Icon(
                        // Vector de biblioteca de fotos
                        imageVector = Icons.Filled.PhotoLibrary,
                        // Sin descripción porque el texto ya indica el total de fotos
                        contentDescription = null,
                        // Color de superficie para el icono
                        tint = MaterialTheme.colorScheme.surface,
                        // Tamaño de 14 dp
                        modifier = Modifier.size(14.dp)
                    )
                    // Separación horizontal de 4 dp entre icono y texto
                    Spacer(modifier = Modifier.width(4.dp))
                    // Muestra el total de fotos recibido desde GaleriaLugarData (sin dato fijo)
                    Text(
                        // Texto dinámico con el total de fotos
                        text = "${data.totalFotos} fotos",
                        // Color de superficie para el texto
                        color = MaterialTheme.colorScheme.surface,
                        // Estilo de etiqueta pequeña del tema
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        // Separación vertical de 10 dp entre la foto principal y las miniaturas
        Spacer(modifier = Modifier.height(10.dp))

        // Fila horizontal desplazable con las miniaturas de la galería
        LazyRow(
            // Márgenes laterales de 16 dp en los extremos de la lista
            contentPadding = PaddingValues(horizontal = 16.dp),
            // Separación de 8 dp entre miniaturas
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Toma como máximo las 3 primeras fotos de la galería para vista previa
            val previewList = data.imagenesGaleria.take(3)
            // Crea una miniatura por cada URL en previewList
            items(previewList) { imgUrl ->
                // Carga cada miniatura de forma asíncrona
                AsyncImage(
                    // URL de la miniatura actual
                    model = imgUrl,
                    // Descripción accesible desde strings.xml
                    contentDescription = stringResource(R.string.cd_miniatura),
                    // Recorta proporcionalmente para llenar el recuadro
                    contentScale = ContentScale.Crop,
                    // Fija tamaño 80x60 dp y esquinas redondeadas de 8 dp
                    modifier = Modifier
                        // Ancho de 80 dp y alto de 60 dp
                        .size(width = 80.dp, height = 60.dp)
                        // Redondea esquinas a 8 dp
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            // Muestra el recuadro final "+N" solo si quedan fotos adicionales por mostrar
            if (data.fotosRestantes > 0) {
                item {
                    // Contenedor para superponer capa oscura y contador sobre la miniatura
                    Box(
                        // Fija tamaño 80x60 dp, esquinas redondeadas y fondo de respaldo
                        modifier = Modifier
                            // Ancho de 80 dp y alto de 60 dp
                            .size(width = 80.dp, height = 60.dp)
                            // Redondea esquinas a 8 dp
                            .clip(RoundedCornerShape(8.dp))
                            // Color de superficie variante del tema
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        // Centra el contenido dentro del Box
                        contentAlignment = Alignment.Center
                    ) {
                        // Si hay cuarta imagen en la galería, la usa como fondo de la miniatura final
                        if (data.imagenesGaleria.size > 3) {
                            // Carga la cuarta imagen de forma asíncrona
                            AsyncImage(
                                // Cuarta URL de la lista
                                model = data.imagenesGaleria[3],
                                // Imagen de fondo decorativa con el contador encima
                                contentDescription = null,
                                // Recorta proporcionalmente
                                contentScale = ContentScale.Crop,
                                // Ocupa todo el tamaño del Box
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        // Capa oscura semitransparente para mejorar el contraste del texto
                        Box(
                            // Ocupa todo el tamaño y aplica fondo oscuro semitransparente
                            modifier = Modifier
                                // Ocupa todo el tamaño disponible
                                .fillMaxSize()
                                // Fondo semitransparente basado en onSurface
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)),
                            // Centra el texto del contador
                            contentAlignment = Alignment.Center
                        ) {
                            // Muestra "+N" calculado dinámicamente desde data.fotosRestantes
                            Text(
                                // Contador dinámico de fotos restantes
                                text = "+${data.fotosRestantes}",
                                // Color de superficie para el texto
                                color = MaterialTheme.colorScheme.surface,
                                // Texto en negrita
                                fontWeight = FontWeight.Bold,
                                // Estilo de título pequeño del tema
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

// Subcomponente que muestra categoría, distintivo opcional, título, ubicación y botón de mapa
@Composable
fun IdentidadLugar(
    // Datos y callbacks de identidad
    data: IdentidadLugarData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Extrae el objeto Lugar desde data
    val lugar = data.lugar

    // Columna principal del bloque de identidad
    Column(modifier = modifier.fillMaxWidth()) {
        // Fila superior con categoría a la izquierda y distintivo opcional a la derecha
        Row(
            // Ocupa todo el ancho disponible
            modifier = Modifier.fillMaxWidth(),
            // Separa ambos extremos
            horizontalArrangement = Arrangement.SpaceBetween,
            // Centra verticalmente ambos bloques
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contenedor con fondo para la categoría del lugar
            Surface(
                // Esquinas redondeadas de 4 dp
                shape = RoundedCornerShape(4.dp),
                // Color de contenedor primario del tema
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                // Muestra la categoría dinámica del lugar
                Text(
                    // Valor de categoría desde lugar
                    text = lugar.categoria,
                    // Color de contenido sobre primaryContainer
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Relleno horizontal de 6 dp y vertical de 2 dp
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            // Muestra el distintivo solo si data.etiquetaDestacado no es nulo ni está en blanco
            data.etiquetaDestacado?.takeIf { it.isNotBlank() }?.let { etiqueta ->
                // Fila con icono de verificación y texto destacado dinámico
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icono decorativo de verificación
                    Icon(
                        // Vector de check circular relleno
                        imageVector = Icons.Filled.CheckCircle,
                        // Sin descripción porque el texto acompaña al icono
                        contentDescription = null,
                        // Color primario del tema
                        tint = MaterialTheme.colorScheme.primary,
                        // Tamaño de 14 dp
                        modifier = Modifier.size(14.dp)
                    )
                    // Separación horizontal de 4 dp entre icono y texto
                    Spacer(modifier = Modifier.width(4.dp))
                    // Muestra el texto destacado recibido en data (sin texto fijo en el composable)
                    Text(
                        // Valor dinámico de etiquetaDestacado
                        text = etiqueta,
                        // Estilo de etiqueta pequeña del tema
                        style = MaterialTheme.typography.labelSmall,
                        // Color primario del tema
                        color = MaterialTheme.colorScheme.primary,
                        // Grosor seminegrita
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Separación vertical de 6 dp antes del título
        Spacer(modifier = Modifier.height(6.dp))

        // Título principal del lugar
        Text(
            // Título dinámico desde lugar
            text = lugar.titulo,
            // Estilo de titular pequeño del tema
            style = MaterialTheme.typography.headlineSmall,
            // Título en negrita
            fontWeight = FontWeight.Bold
        )

        // Separación vertical de 4 dp antes de la fila de ubicación y mapa
        Spacer(modifier = Modifier.height(4.dp))

        // Fila con la ubicación a la izquierda y el botón "Ver en mapa" a la derecha
        Row(
            // Ocupa todo el ancho disponible
            modifier = Modifier.fillMaxWidth(),
            // Separa ambos extremos
            horizontalArrangement = Arrangement.SpaceBetween,
            // Centra verticalmente ambos elementos
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sub-fila con icono y texto de ubicación
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icono decorativo de ubicación
                Icon(
                    // Vector de pin de ubicación
                    imageVector = Icons.Filled.LocationOn,
                    // Sin descripción porque el texto ya muestra la ubicación
                    contentDescription = null,
                    // Color secundario sobre superficie
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    // Tamaño de 16 dp
                    modifier = Modifier.size(16.dp)
                )
                // Separación horizontal de 4 dp entre icono y texto
                Spacer(modifier = Modifier.width(4.dp))
                // Texto con la ubicación dinámica del lugar
                Text(
                    // Ubicación desde lugar
                    text = lugar.ubicacion,
                    // Estilo de cuerpo pequeño del tema
                    style = MaterialTheme.typography.bodySmall,
                    // Color secundario sobre superficie
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botón de texto para abrir el lugar en el mapa
            TextButton(
                // Ejecuta el callback al pulsar
                onClick = data.onVerEnMapaClick,
                // Sin padding adicional alrededor del texto
                contentPadding = PaddingValues(0.dp)
            ) {
                // Texto del botón desde strings.xml
                Text(
                    // Recurso de texto "Ver en mapa"
                    text = stringResource(R.string.detalle_ver_en_mapa),
                    // Estilo de etiqueta mediana del tema
                    style = MaterialTheme.typography.labelMedium,
                    // Texto en negrita
                    fontWeight = FontWeight.Bold,
                    // Color primario del tema
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// Subcomponente que muestra el resumen de valoración y el botón de opiniones
@Composable
fun ResumenValoracion(
    // Datos y callbacks de valoración
    data: ResumenValoracionData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Tarjeta contenedora del resumen de valoración
    Card(
        // Ocupa todo el ancho y aplica el modifier recibido
        modifier = modifier.fillMaxWidth(),
        // Esquinas redondeadas de 12 dp
        shape = RoundedCornerShape(12.dp),
        // Fondo suave basado en surfaceVariant
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
    ) {
        // Fila principal con bloque de puntuación a la izquierda y botón de opiniones a la derecha
        Row(
            // Ocupa todo el ancho y añade 14 dp de relleno interior
            modifier = Modifier
                // Ocupa todo el ancho disponible
                .fillMaxWidth()
                // Relleno interior de 14 dp en todos los lados
                .padding(14.dp),
            // Centra verticalmente los elementos
            verticalAlignment = Alignment.CenterVertically,
            // Separa los extremos izquierdo y derecho
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Agrupa recuadro de puntuación y textos descriptivos
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Recuadro cuadrado destacado con la nota numérica
                Surface(
                    // Esquinas redondeadas de 8 dp
                    shape = RoundedCornerShape(8.dp),
                    // Fondo con el color primario del tema
                    color = MaterialTheme.colorScheme.primary,
                    // Tamaño fijo de 46x46 dp
                    modifier = Modifier.size(46.dp)
                ) {
                    // Centra la nota numérica dentro del recuadro
                    Box(contentAlignment = Alignment.Center) {
                        // Muestra la valoración numérica desde data
                        Text(
                            // Valor numérico convertido a texto
                            text = "${data.valoracion}",
                            // Color de superficie para buen contraste
                            color = MaterialTheme.colorScheme.surface,
                            // Texto en negrita
                            fontWeight = FontWeight.Bold,
                            // Estilo de título mediano del tema
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Separación horizontal de 12 dp entre el recuadro y los textos
                Spacer(modifier = Modifier.width(12.dp))

                // Columna con etiqueta descriptiva de valoración y total de reseñas
                Column {
                    // Fila con etiqueta dinámica de valoración y estrella
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Muestra la etiqueta recibida en data (sin texto fijo en el composable)
                        Text(
                            // Valor dinámico de etiquetaValoracion
                            text = data.etiquetaValoracion,
                            // Estilo de título pequeño del tema
                            style = MaterialTheme.typography.titleSmall,
                            // Texto en negrita
                            fontWeight = FontWeight.Bold
                        )
                        // Separación horizontal de 4 dp entre texto e icono
                        Spacer(modifier = Modifier.width(4.dp))
                        // Icono decorativo de estrella
                        Icon(
                            // Vector de estrella rellena
                            imageVector = Icons.Filled.Star,
                            // Sin descripción porque el texto indica la valoración
                            contentDescription = null,
                            // Color primario del tema
                            tint = MaterialTheme.colorScheme.primary,
                            // Tamaño de 16 dp
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    // Muestra el texto formateado con el número de reseñas desde strings.xml
                    Text(
                        // Formatea la cadena de strings.xml con data.numResenas
                        text = stringResource(R.string.detalle_basado_en_resenas, data.numResenas),
                        // Estilo de cuerpo pequeño del tema
                        style = MaterialTheme.typography.bodySmall,
                        // Color secundario sobre superficie
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Botón con borde para abrir las opiniones
            OutlinedButton(
                // Ejecuta el callback de opiniones definido en data
                onClick = data.onOpinionesClick,
                // Esquinas redondeadas de 8 dp
                shape = RoundedCornerShape(8.dp),
                // Relleno interior horizontal de 12 dp y vertical de 6 dp
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                // Texto del botón desde strings.xml
                Text(
                    // Recurso de texto "Opiniones"
                    text = stringResource(R.string.detalle_opiniones),
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

// Subcomponente que muestra las condiciones climáticas y del entorno del día
@Composable
fun CondicionesHoy(
    // Datos de las condiciones climáticas
    data: CondicionesHoyData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Extrae el objeto CondicionesClima desde data
    val condiciones = data.condiciones

    // Tarjeta contenedora de las condiciones meteorológicas
    Card(
        // Ocupa todo el ancho y aplica el modifier recibido
        modifier = modifier.fillMaxWidth(),
        // Esquinas redondeadas de 12 dp
        shape = RoundedCornerShape(12.dp),
        // Color de superficie del tema
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Elevación suave de 2 dp
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // Columna interior con relleno de 14 dp en todos los lados
        Column(
            // Relleno interior de 14 dp
            modifier = Modifier.padding(14.dp)
        ) {
            // Fila superior con título, icono de sol y estado general del clima
            Row(
                // Ocupa todo el ancho disponible
                modifier = Modifier.fillMaxWidth(),
                // Separa ambos extremos
                horizontalArrangement = Arrangement.SpaceBetween,
                // Centra verticalmente los elementos
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Agrupa título e icono de sol
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Título de sección desde strings.xml
                    Text(
                        // Recurso de texto "Condiciones hoy"
                        text = stringResource(R.string.detalle_condiciones_hoy),
                        // Estilo de título pequeño del tema
                        style = MaterialTheme.typography.titleSmall,
                        // Texto en negrita
                        fontWeight = FontWeight.Bold
                    )
                    // Separación horizontal de 6 dp entre texto e icono
                    Spacer(modifier = Modifier.width(6.dp))
                    // Icono de sol
                    Icon(
                        // Vector de sol
                        imageVector = Icons.Filled.WbSunny,
                        // Descripción accesible desde strings.xml
                        contentDescription = stringResource(R.string.cd_sol),
                        // Color primario del tema
                        tint = MaterialTheme.colorScheme.primary,
                        // Tamaño de 18 dp
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Etiqueta con el estado general de las condiciones
                Surface(
                    // Forma de píldora circular
                    shape = CircleShape,
                    // Fondo de contenedor primario del tema
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    // Muestra el estado dinámico desde condiciones
                    Text(
                        // Estado de las condiciones
                        text = condiciones.estado,
                        // Color de contenido sobre primaryContainer
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        // Estilo de etiqueta pequeña del tema
                        style = MaterialTheme.typography.labelSmall,
                        // Texto en negrita
                        fontWeight = FontWeight.Bold,
                        // Relleno interior horizontal de 8 dp y vertical de 2 dp
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            // Separación vertical de 12 dp antes de la primera fila de datos
            Spacer(modifier = Modifier.height(12.dp))

            // Primera fila con estado del agua a la izquierda y temperatura a la derecha
            Row(
                // Ocupa todo el ancho disponible
                modifier = Modifier.fillMaxWidth(),
                // Separa ambos bloques a los extremos
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Sub-fila con icono y texto del estado del agua
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icono decorativo de agua
                    Icon(
                        // Vector de ondas de agua
                        imageVector = Icons.Filled.Water,
                        // Sin descripción porque el texto ya lo indica
                        contentDescription = null,
                        // Color primario del tema
                        tint = MaterialTheme.colorScheme.primary,
                        // Tamaño de 16 dp
                        modifier = Modifier.size(16.dp)
                    )
                    // Separación horizontal de 6 dp entre icono y texto
                    Spacer(modifier = Modifier.width(6.dp))
                    // Muestra el estado del agua
                    Text(
                        // Valor de agua desde condiciones
                        text = condiciones.agua,
                        // Estilo de cuerpo pequeño del tema
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Muestra la temperatura registrada
                Text(
                    // Valor de temperatura desde condiciones
                    text = condiciones.temperatura,
                    // Estilo de cuerpo pequeño del tema
                    style = MaterialTheme.typography.bodySmall,
                    // Texto en negrita
                    fontWeight = FontWeight.Bold
                )
            }

            // Separación vertical de 8 dp entre filas
            Spacer(modifier = Modifier.height(8.dp))

            // Segunda fila con oleaje a la izquierda e índice UV a la derecha
            Row(
                // Ocupa todo el ancho disponible
                modifier = Modifier.fillMaxWidth(),
                // Separa ambos bloques a los extremos
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Sub-fila con icono y texto del oleaje
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icono decorativo de viento/oleaje
                    Icon(
                        // Vector de ráfaga de aire
                        imageVector = Icons.Filled.Air,
                        // Sin descripción porque el texto ya indica el oleaje
                        contentDescription = null,
                        // Color secundario del tema
                        tint = MaterialTheme.colorScheme.secondary,
                        // Tamaño de 16 dp
                        modifier = Modifier.size(16.dp)
                    )
                    // Separación horizontal de 6 dp entre icono y texto
                    Spacer(modifier = Modifier.width(6.dp))
                    // Muestra el estado del oleaje
                    Text(
                        // Valor de oleaje desde condiciones
                        text = condiciones.oleaje,
                        // Estilo de cuerpo pequeño del tema
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Muestra el índice UV
                Text(
                    // Valor de uv desde condiciones
                    text = condiciones.uv,
                    // Estilo de cuerpo pequeño del tema
                    style = MaterialTheme.typography.bodySmall,
                    // Color primario del tema
                    color = MaterialTheme.colorScheme.primary,
                    // Texto en negrita
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Subcomponente de descripción expandible del lugar
@Composable
fun DescripcionLugar(
    // Datos de la descripción
    data: DescripcionLugarData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Estado visual local que controla si la descripción está expandida o contraída
    var expandido by remember { mutableStateOf(false) }

    // Columna que agrupa el texto descriptivo y el botón de alternar expansión
    Column(modifier = modifier.fillMaxWidth()) {
        // Muestra la descripción limitada a 3 líneas cuando está contraída
        Text(
            // Texto descriptivo desde data
            text = data.descripcion,
            // Estilo de cuerpo mediano del tema
            style = MaterialTheme.typography.bodyMedium,
            // Sin límite de líneas si está expandido, o 3 líneas si está contraído
            maxLines = if (expandido) Int.MAX_VALUE else 3,
            // Muestra puntos suspensivos cuando se corta el texto
            overflow = TextOverflow.Ellipsis,
            // Color secundario sobre superficie
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Separación vertical de 6 dp entre la descripción y el enlace
        Spacer(modifier = Modifier.height(6.dp))

        // Enlace interactivo para expandir o contraer la descripción
        Text(
            // Cambia el texto entre "Leer menos" y "Leer más" según expandido
            text = if (expandido) {
                stringResource(R.string.detalle_leer_menos)
            } else {
                stringResource(R.string.detalle_leer_mas)
            },
            // Color primario del tema
            color = MaterialTheme.colorScheme.primary,
            // Estilo de etiqueta mediana del tema
            style = MaterialTheme.typography.labelMedium,
            // Texto en negrita
            fontWeight = FontWeight.Bold,
            // Alterna el estado expandido al pulsar
            modifier = Modifier.clickable { expandido = !expandido }
        )
    }
}

// Subcomponente que muestra los servicios disponibles y su contador
@Composable
fun ServiciosLugar(
    // Datos de servicios
    data: ServiciosLugarData,
    // Modificador opcional externo aplicado en la raíz (C2 y C3)
    modifier: Modifier = Modifier
) {
    // Extrae la lista de servicios desde data
    val servicios = data.servicios

    // Columna principal de la sección de servicios
    Column(modifier = modifier.fillMaxWidth()) {
        // Fila superior con título de sección y contador de puntos/servicios
        Row(
            // Ocupa todo el ancho disponible
            modifier = Modifier.fillMaxWidth(),
            // Separa título y contador a los extremos
            horizontalArrangement = Arrangement.SpaceBetween,
            // Centra verticalmente ambos textos
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Título de la sección desde strings.xml
            Text(
                // Recurso de texto de servicios
                text = stringResource(R.string.detalle_servicios_titulo),
                // Estilo de título mediano del tema
                style = MaterialTheme.typography.titleMedium,
                // Texto en negrita
                fontWeight = FontWeight.Bold
            )

            // Contador formateado con la cantidad de servicios desde strings.xml
            Text(
                // Pasa el tamaño de la lista servicios
                text = stringResource(R.string.detalle_servicios_puntos, servicios.size),
                // Estilo de etiqueta pequeña del tema
                style = MaterialTheme.typography.labelSmall,
                // Color secundario sobre superficie
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                // Texto en negrita
                fontWeight = FontWeight.Bold
            )
        }

        // Separación vertical de 12 dp antes de la lista horizontal de servicios
        Spacer(modifier = Modifier.height(12.dp))

        // Lista horizontal con desplazamiento para las tarjetas de servicio
        LazyRow(
            // Separación de 10 dp entre tarjetas
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Genera una TarjetaServicio por cada servicio en la lista
            items(servicios) { servicio ->
                // Pinta la tarjeta pasando TarjetaServicioData (C1)
                TarjetaServicio(data = TarjetaServicioData(servicio = servicio))
            }
        }
    }
}

// Crea un ejemplo del Blue Lagoon del mockup de detalle para previsualizar esta sección/pantalla (C5)
private fun blueLagoonDetallePreviewLugar(): Lugar = Lugar(
    // Identificador del lugar
    id = "blue-lagoon",
    // Nombre principal del lugar según el mockup de detalle
    titulo = "Blue Lagoon (Laguna Azul)",
    // Categoría turística según el mockup
    categoria = "Playas y Calas",
    // Ubicación dentro del archipiélago según el mockup
    ubicacion = "Isla de Comino, República de Malta",
    // Valoración media según el mockup
    valoracion = 4.8,
    // Cantidad de reseñas según el mockup
    numResenas = "2.450",
    // Descripción corta para listados
    descripcionCorta = "Aguas cristalinas turquesas entre Comino y Gozo, ideales para baño y snorkel.",
    // Descripción detallada entre Comino y Gozo según el mockup
    descripcionCompleta = "La Laguna Azul es el rincón más icónico y deslumbrante del archipiélago maltés. Encajada entre la isla de Comino y la isla de Gozo, forma una espectacular piscina natural de aguas turquesas brillantes y fondo de arena blanca calcárea.",
    // URL principal de ejemplo
    imagenUrl = "https://example.com/blue-lagoon.jpg",
    // Lista de imágenes para galería
    fotosGaleria = listOf(
        "https://example.com/blue-lagoon-1.jpg",
        "https://example.com/blue-lagoon-2.jpg"
    ),
    // Estado inicial de favorito según el mockup
    esFavorito = true,
    // Condiciones climáticas según el mockup
    condicionesHoy = CondicionesClima(
        estado = "Despejado",
        agua = "Agua calma",
        oleaje = "Oleaje bajo",
        temperatura = "28 °C",
        uv = "UV 7 (Alto)"
    ),
    // Servicios del mockup del Blue Lagoon
    servicios = listOf(
        Servicio(
            id = "acceso-barco",
            titulo = "Acceso en Barco",
            descripcion = "Ferries regulares diarios",
            tipoIcono = TipoServicioIcono.BARCO
        ),
        Servicio(
            id = "snorkel-cuevas",
            titulo = "Snorkel y Cuevas",
            descripcion = "Aguas transparentes",
            tipoIcono = TipoServicioIcono.SNORKEL
        )
    )
)

// Preview de ServiciosLugar con los servicios del Blue Lagoon del mockup (C5)
@Preview(showBackground = true)
@Composable
private fun ServiciosLugarPreview() {
    // Aplica el tema para la previsualización
    MaterialTheme {
        // Renderiza ServiciosLugar con los servicios del ejemplo
        ServiciosLugar(
            data = ServiciosLugarData(
                servicios = blueLagoonDetallePreviewLugar().servicios
            )
        )
    }
}
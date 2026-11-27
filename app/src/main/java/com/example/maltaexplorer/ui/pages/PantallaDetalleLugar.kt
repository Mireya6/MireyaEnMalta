
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.maltaexplorer.R
import com.example.maltaexplorer.model.CondicionesClima
import com.example.maltaexplorer.model.Lugar
import com.example.maltaexplorer.model.Servicio
import com.example.maltaexplorer.ui.components.TarjetaServicio

// Declara la pantalla principal de detalle de un lugar turístico
@Composable
// Función Composable que recibe el lugar y los eventos de interacción
fun PantallaDetalleLugar(
    // Objeto con toda la información del lugar a mostrar
    lugar: Lugar,
    // Función que se ejecuta al pulsar el botón de volver
    onVolverClick: () -> Unit,
    // Función que se ejecuta al pulsar el botón de compartir
    onCompartirClick: () -> Unit,
    // Función que se ejecuta al cambiar el estado de favorito
    onToggleFavorito: () -> Unit,
    // Función que se ejecuta al pulsar "Ver en mapa"
    onVerEnMapaClick: () -> Unit,
    // Modificador opcional para personalizar el contenedor desde fuera
    modifier: Modifier = Modifier
) {
    // Crea y recuerda el estado de desplazamiento vertical de la pantalla
    val scrollState = rememberScrollState()

    // Contenedor principal en columna con desplazamiento vertical
    Column(
        // Configura tamaño, fondo y scroll del contenedor raíz
        modifier = modifier
            // Ocupa todo el ancho y alto disponible
            .fillMaxSize()
            // Pinta el fondo con el color de fondo del tema
            .background(MaterialTheme.colorScheme.background)
            // Activa el scroll vertical usando scrollState
            .verticalScroll(scrollState)
    ) {
        // Pinta la cabecera superior de la pantalla de detalle
        CabeceraDetalle(
            // Conecta el evento de volver atrás
            onVolver = onVolverClick,
            // Conecta el evento de compartir lugar
            onCompartir = onCompartirClick,
            // Conecta el evento de alternar favorito
            onFavorito = onToggleFavorito,
            // Indica si el lugar está actualmente marcado como favorito
            esFavorito = lugar.esFavorito
        )

        // Pinta la galería de imágenes del lugar
        GaleriaLugar(
            // Pasa la URL de la fotografía principal
            imagenPrincipal = lugar.imagenUrl,
            // Pasa la lista de fotos secundarias
            imagenesGaleria = lugar.fotosGaleria,
            // Pasa la categoría del lugar
            categoria = lugar.categoria
        )

        // Inserta un espacio vertical de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Pinta el bloque de identidad con categoría, título, ubicación y mapa
        IdentidadLugar(
            // Pasa el objeto completo del lugar
            lugar = lugar,
            // Conecta la acción para abrir el mapa
            onVerEnMapaClick = onVerEnMapaClick,
            // Aplica margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Inserta un espacio vertical de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Pinta la tarjeta con puntuación, reseñas y botón de opiniones
        ResumenValoracion(
            // Pasa la puntuación numérica del lugar
            valoracion = lugar.valoracion,
            // Pasa el texto con el número de reseñas
            numResenas = lugar.numResenas,
            // Aplica margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Inserta un espacio vertical de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Pinta la tarjeta con las condiciones meteorológicas actuales
        CondicionesHoy(
            // Pasa los datos climáticos del lugar
            condiciones = lugar.condicionesHoy,
            // Aplica margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Inserta un espacio vertical de 16 dp
        Spacer(modifier = Modifier.height(16.dp))

        // Pinta la descripción completa con opción de expandir o contraer
        DescripcionLugar(
            // Pasa el texto descriptivo del lugar
            descripcion = lugar.descripcionCompleta,
            // Aplica margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Inserta un espacio vertical de 20 dp
        Spacer(modifier = Modifier.height(20.dp))

        // Pinta la sección de servicios disponibles y su contador
        ServiciosLugar(
            // Pasa la lista de servicios del lugar
            servicios = lugar.servicios,
            // Aplica margen horizontal de 16 dp
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Inserta un espacio vertical de 32 dp al final de la pantalla
        Spacer(modifier = Modifier.height(32.dp))
    }
}

// Declara el subcomponente de cabecera superior de la pantalla de detalle
@Composable
// Función Composable para la barra superior con volver, título y acciones
fun CabeceraDetalle(
    // Callback ejecutado al pulsar volver
    onVolver: () -> Unit,
    // Callback ejecutado al pulsar compartir
    onCompartir: () -> Unit,
    // Callback ejecutado al pulsar favorito
    onFavorito: () -> Unit,
    // Indica si el lugar es favorito
    esFavorito: Boolean,
    // Modificador opcional para personalizar la cabecera desde fuera
    modifier: Modifier = Modifier
) {
    // Fila que distribuye los elementos de la cabecera horizontalmente
    Row(
        // Configura ancho completo, margen de barra de estado y padding
        modifier = modifier
            // Ocupa todo el ancho disponible
            .fillMaxWidth()
            // Evita solaparse con la barra de estado superior del sistema
            .statusBarsPadding()
            // Añade relleno horizontal de 8 dp y vertical de 8 dp
            .padding(horizontal = 8.dp, vertical = 8.dp),
        // Centra verticalmente los elementos de la fila
        verticalAlignment = Alignment.CenterVertically,
        // Separa los extremos izquierdo, centro y derecho
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Botón con icono para volver atrás
        IconButton(onClick = onVolver) {
            // Dibuja la flecha hacia atrás con descripción accesible desde strings.xml
            Icon(
                // Vector de flecha atrás adaptado a dirección de lectura
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                // Descripción accesible leída desde strings.xml
                contentDescription = stringResource(R.string.cd_volver)
            )
        }

        // Muestra el título de la cabecera leído desde strings.xml
        Text(
            // Texto fijo obtenido de los recursos de cadena
            text = stringResource(R.string.detalle_titulo),
            // Aplica el estilo de título mediano del tema
            style = MaterialTheme.typography.titleMedium,
            // Pone el título en negrita
            fontWeight = FontWeight.Bold
        )

        // Agrupa horizontalmente los botones de favorito y compartir
        Row {
            // Botón para marcar o desmarcar favorito
            IconButton(onClick = onFavorito) {
                // Dibuja el icono de corazón relleno o vacío según esFavorito
                Icon(
                    // Selecciona el vector según el estado de favorito
                    imageVector = if (esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    // Descripción accesible leída desde strings.xml
                    contentDescription = stringResource(R.string.cd_favorito),
                    // Pinta con el color primario si es favorito o con onSurface si no
                    tint = if (esFavorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
            // Botón para compartir el lugar
            IconButton(onClick = onCompartir) {
                // Dibuja el icono de compartir con descripción desde strings.xml
                Icon(
                    // Vector de compartir
                    imageVector = Icons.Filled.Share,
                    // Descripción accesible leída desde strings.xml
                    contentDescription = stringResource(R.string.cd_compartir)
                )
            }
        }
    }
}

// Declara el subcomponente de galería con imagen principal y miniaturas
@Composable
// Función Composable que muestra las fotos del lugar
fun GaleriaLugar(
    // URL de la foto principal
    imagenPrincipal: String,
    // Lista de URLs de fotos adicionales
    imagenesGaleria: List<String>,
    // Categoría del lugar
    categoria: String,
    // Modificador opcional externo
    modifier: Modifier = Modifier
) {
    // Columna que organiza la foto principal arriba y las miniaturas abajo
    Column(modifier = modifier.fillMaxWidth()) {
        // Contenedor para superponer etiquetas sobre la imagen principal
        Box(
            // Configura ancho, alto, márgenes laterales y esquinas redondeadas
            modifier = Modifier
                // Ocupa todo el ancho disponible
                .fillMaxWidth()
                // Fija una altura de 230 dp
                .height(230.dp)
                // Añade margen lateral de 16 dp
                .padding(horizontal = 16.dp)
                // Recorta las esquinas con un radio de 16 dp
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Carga de forma asíncrona la fotografía principal
            AsyncImage(
                // URL de la imagen principal
                model = imagenPrincipal,
                // Descripción accesible leída desde strings.xml
                contentDescription = stringResource(R.string.cd_vista_aerea),
                // Recorta manteniendo proporción para rellenar el espacio
                contentScale = ContentScale.Crop,
                // Ocupa todo el tamaño del Box
                modifier = Modifier.fillMaxSize()
            )

            // Etiqueta flotante superior izquierda para la categoría
            Surface(
                // Fondo oscuro semitransparente basado en onSurface del tema
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                // Esquinas redondeadas de 6 dp
                shape = RoundedCornerShape(6.dp),
                // Posiciona arriba a la izquierda con margen de 12 dp
                modifier = Modifier
                    // Alinea en la esquina superior izquierda
                    .align(Alignment.TopStart)
                    // Añade relleno exterior de 12 dp
                    .padding(12.dp)
            ) {
                // Muestra la categoría en mayúsculas
                Text(
                    // Convierte la categoría a mayúsculas
                    text = categoria.uppercase(),
                    // Color de superficie para contrastar sobre el fondo oscuro
                    color = MaterialTheme.colorScheme.surface,
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Texto en negrita
                    fontWeight = FontWeight.Bold,
                    // Relleno interior horizontal de 8 dp y vertical de 4 dp
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Etiqueta flotante inferior derecha con el total de fotos
            Surface(
                // Fondo oscuro semitransparente basado en onSurface del tema
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                // Esquinas redondeadas de 6 dp
                shape = RoundedCornerShape(6.dp),
                // Posiciona abajo a la derecha con margen de 12 dp
                modifier = Modifier
                    // Alinea en la esquina inferior derecha
                    .align(Alignment.BottomEnd)
                    // Añade relleno exterior de 12 dp
                    .padding(12.dp)
            ) {
                // Fila que coloca el icono de galería junto al número de fotos
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
                        // Sin descripción porque el texto ya indica el número de fotos
                        contentDescription = null,
                        // Color de superficie para el icono
                        tint = MaterialTheme.colorScheme.surface,
                        // Tamaño de 14 dp
                        modifier = Modifier.size(14.dp)
                    )
                    // Espacio horizontal de 4 dp entre el icono y el texto
                    Spacer(modifier = Modifier.width(4.dp))
                    // Muestra el texto de fotos desde strings.xml
                    Text(
                        // Lee el texto fijo "12 fotos" desde strings.xml
                        text = stringResource(R.string.detalle_contador_fotos),
                        // Color de superficie para el texto
                        color = MaterialTheme.colorScheme.surface,
                        // Estilo de etiqueta pequeña del tema
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        // Espacio vertical de 10 dp entre la imagen grande y las miniaturas
        Spacer(modifier = Modifier.height(10.dp))

        // Fila horizontal con desplazamiento para las miniaturas
        LazyRow(
            // Márgenes horizontales de 16 dp en los extremos de la lista
            contentPadding = PaddingValues(horizontal = 16.dp),
            // Separación de 8 dp entre miniaturas
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Toma como máximo las 3 primeras imágenes de la galería sin invocar remember fuera de contexto composable
            val previewList = imagenesGaleria.take(3)
            // Genera una miniatura por cada URL de previewList
            items(previewList) { imgUrl ->
                // Carga cada miniatura de forma asíncrona
                AsyncImage(
                    // URL de la miniatura actual
                    model = imgUrl,
                    // Descripción accesible leída desde strings.xml
                    contentDescription = stringResource(R.string.cd_miniatura),
                    // Recorta manteniendo proporción
                    contentScale = ContentScale.Crop,
                    // Fija tamaño 80x60 dp y bordes redondeados de 8 dp
                    modifier = Modifier
                        // Ancho de 80 dp y alto de 60 dp
                        .size(width = 80.dp, height = 60.dp)
                        // Redondea las esquinas a 8 dp
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            // Añade el último elemento de la fila con el indicador "+8"
            item {
                // Contenedor para superponer la capa oscura y el número sobre la miniatura
                Box(
                    // Fija tamaño 80x60 dp, esquinas redondeadas y fondo surfaceVariant
                    modifier = Modifier
                        // Ancho de 80 dp y alto de 60 dp
                        .size(width = 80.dp, height = 60.dp)
                        // Redondea las esquinas a 8 dp
                        .clip(RoundedCornerShape(8.dp))
                        // Color de respaldo de superficie variante del tema
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    // Centra el contenido en el Box
                    contentAlignment = Alignment.Center
                ) {
                    // Comprueba si existen al menos 4 imágenes en la galería
                    if (imagenesGaleria.size > 3) {
                        // Carga la cuarta imagen como fondo de la última miniatura
                        AsyncImage(
                            // Cuarta URL de la lista
                            model = imagenesGaleria[3],
                            // Imagen de fondo decorativa con el contador encima
                            contentDescription = null,
                            // Recorta manteniendo proporción
                            contentScale = ContentScale.Crop,
                            // Ocupa todo el tamaño del Box
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    // Capa oscura semitransparente para hacer legible el texto "+8"
                    Box(
                        // Ocupa todo el tamaño y aplica fondo onSurface al 60% de opacidad
                        modifier = Modifier
                            // Ocupa todo el tamaño disponible
                            .fillMaxSize()
                            // Fondo oscuro semitransparente basado en el tema
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)),
                        // Centra el texto "+8"
                        contentAlignment = Alignment.Center
                    ) {
                        // Muestra "+8" leído desde strings.xml
                        Text(
                            // Lee el texto fijo "+8" desde strings.xml
                            text = stringResource(R.string.detalle_mas_fotos),
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

// Declara el subcomponente con categoría, distintivo, título y ubicación
@Composable
// Función Composable que muestra los datos principales de identidad del lugar
fun IdentidadLugar(
    // Objeto Lugar con categoría, título y ubicación
    lugar: Lugar,
    // Callback ejecutado al pulsar "Ver en mapa"
    onVerEnMapaClick: () -> Unit,
    // Modificador opcional externo
    modifier: Modifier = Modifier
) {
    // Columna principal del bloque de identidad
    Column(modifier = modifier.fillMaxWidth()) {
        // Fila superior con la categoría a la izquierda y el distintivo a la derecha
        Row(
            // Ocupa todo el ancho disponible
            modifier = Modifier.fillMaxWidth(),
            // Separa los extremos izquierdo y derecho
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
                    // Valor de categoría del objeto lugar
                    text = lugar.categoria,
                    // Color de contenido sobre primaryContainer
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Relleno horizontal de 6 dp y vertical de 2 dp
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            // Fila con icono de check y texto de destino imprescindible
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icono decorativo de verificación
                Icon(
                    // Vector de check circular relleno
                    imageVector = Icons.Filled.CheckCircle,
                    // Sin descripción porque va acompañado del texto descriptivo
                    contentDescription = null,
                    // Color primario del tema
                    tint = MaterialTheme.colorScheme.primary,
                    // Tamaño de 14 dp
                    modifier = Modifier.size(14.dp)
                )
                // Espacio horizontal de 4 dp entre el icono y el texto
                Spacer(modifier = Modifier.width(4.dp))
                // Muestra "Destino imprescindible" desde strings.xml
                Text(
                    // Texto fijo leído de strings.xml
                    text = stringResource(R.string.detalle_destino_imprescindible),
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall,
                    // Color primario del tema
                    color = MaterialTheme.colorScheme.primary,
                    // Grosor seminegrita
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Espacio vertical de 6 dp antes del título
        Spacer(modifier = Modifier.height(6.dp))

        // Muestra el título principal del lugar
        Text(
            // Título dinámico del objeto lugar
            text = lugar.titulo,
            // Estilo de titular pequeño del tema
            style = MaterialTheme.typography.headlineSmall,
            // Título en negrita
            fontWeight = FontWeight.Bold
        )

        // Espacio vertical de 4 dp antes de la fila de ubicación y mapa
        Spacer(modifier = Modifier.height(4.dp))

        // Fila con la ubicación a la izquierda y el botón "Ver en mapa" a la derecha
        Row(
            // Ocupa todo el ancho disponible
            modifier = Modifier.fillMaxWidth(),
            // Separa los extremos izquierdo y derecho
            horizontalArrangement = Arrangement.SpaceBetween,
            // Centra verticalmente ambos elementos
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sub-fila con pin de ubicación y texto de ubicación
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icono decorativo de ubicación
                Icon(
                    // Vector de pin de ubicación
                    imageVector = Icons.Filled.LocationOn,
                    // Sin descripción porque el texto muestra la ubicación
                    contentDescription = null,
                    // Color secundario sobre superficie
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    // Tamaño de 16 dp
                    modifier = Modifier.size(16.dp)
                )
                // Espacio horizontal de 4 dp entre el pin y el texto
                Spacer(modifier = Modifier.width(4.dp))
                // Muestra la ubicación dinámica del lugar
                Text(
                    // Ubicación del objeto lugar
                    text = lugar.ubicacion,
                    // Estilo de cuerpo pequeño del tema
                    style = MaterialTheme.typography.bodySmall,
                    // Color secundario sobre superficie
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botón de texto para abrir el lugar en el mapa
            TextButton(
                // Ejecuta el callback onVerEnMapaClick al pulsar
                onClick = onVerEnMapaClick,
                // Elimina relleno extra alrededor del texto del botón
                contentPadding = PaddingValues(0.dp)
            ) {
                // Muestra "Ver en mapa" desde strings.xml
                Text(
                    // Texto leído desde strings.xml
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

// Declara el subcomponente con el resumen de valoración y botón de opiniones
@Composable
// Función Composable que muestra puntuación, reseñas y acceso a opiniones
fun ResumenValoracion(
    // Puntuación numérica media del lugar
    valoracion: Double,
    // Número de reseñas en formato texto
    numResenas: String,
    // Modificador opcional externo
    modifier: Modifier = Modifier
) {
    // Tarjeta contenedora del resumen de valoración
    Card(
        // Ocupa todo el ancho y aplica el modifier recibido
        modifier = modifier.fillMaxWidth(),
        // Esquinas redondeadas de 12 dp
        shape = RoundedCornerShape(12.dp),
        // Color de fondo suave basado en surfaceVariant
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
    ) {
        // Fila principal que reparte el bloque de puntuación y el botón de opiniones
        Row(
            // Ocupa todo el ancho y añade 14 dp de relleno interior
            modifier = Modifier
                // Ocupa todo el ancho disponible
                .fillMaxWidth()
                // Relleno interior de 14 dp en todos los lados
                .padding(14.dp),
            // Centra verticalmente los elementos de la fila
            verticalAlignment = Alignment.CenterVertically,
            // Separa el bloque izquierdo del botón derecho
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Agrupa el recuadro de puntuación y los textos descriptivos
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Recuadro cuadrado con la nota numérica destacada
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
                        // Muestra el valor numérico de la valoración
                        Text(
                            // Convierte la valoración a texto
                            text = "$valoracion",
                            // Texto con color de superficie del tema para buen contraste
                            color = MaterialTheme.colorScheme.surface,
                            // Texto en negrita
                            fontWeight = FontWeight.Bold,
                            // Tamaño de fuente de 18 sp
                            fontSize = 18.sp
                        )
                    }
                }

                // Espacio horizontal de 12 dp entre el recuadro y los textos
                Spacer(modifier = Modifier.width(12.dp))

                // Columna con la calificación y el número de reseñas
                Column {
                    // Fila con la palabra "Excelente" y una estrella
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Muestra "Excelente" desde strings.xml
                        Text(
                            // Texto leído de strings.xml
                            text = stringResource(R.string.detalle_valoracion_excelente),
                            // Estilo de título pequeño del tema
                            style = MaterialTheme.typography.titleSmall,
                            // Texto en negrita
                            fontWeight = FontWeight.Bold
                        )
                        // Espacio horizontal de 4 dp entre el texto y la estrella
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
                    // Muestra "Basado en X reseñas" desde strings.xml
                    Text(
                        // Formatea la cadena de strings.xml con numResenas
                        text = stringResource(R.string.detalle_basado_en_resenas, numResenas),
                        // Estilo de cuerpo pequeño del tema
                        style = MaterialTheme.typography.bodySmall,
                        // Color secundario sobre superficie
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Botón con borde para abrir las opiniones
            OutlinedButton(
                // Acción vacía temporal al pulsar el botón de opiniones
                onClick = { },
                // Esquinas redondeadas de 8 dp
                shape = RoundedCornerShape(8.dp),
                // Relleno interior horizontal de 12 dp y vertical de 6 dp
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                // Muestra "Opiniones" desde strings.xml
                Text(
                    // Texto leído de strings.xml
                    text = stringResource(R.string.detalle_opiniones),
                    // Estilo de etiqueta pequeña del tema
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

// Declara el subcomponente que muestra las condiciones del entorno y el clima
@Composable
// Función Composable que renderiza estado, agua, oleaje, temperatura y UV
fun CondicionesHoy(
    // Objeto con los datos climáticos del día
    condiciones: CondicionesClima,
    // Modificador opcional externo
    modifier: Modifier = Modifier
) {
    // Tarjeta contenedora de las condiciones meteorológicas
    Card(
        // Ocupa todo el ancho y aplica el modifier recibido
        modifier = modifier.fillMaxWidth(),
        // Esquinas redondeadas de 12 dp
        shape = RoundedCornerShape(12.dp),
        // Color de superficie de Material 3
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Sombra suave de 2 dp
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // Columna interior que organiza las filas de condiciones con relleno de 14 dp
        Column(
            // Aplica 14 dp de relleno interior en todos los lados
            modifier = Modifier.padding(14.dp)
        ) {
            // Fila superior con título, icono de sol y etiqueta de estado
            Row(
                // Ocupa todo el ancho disponible
                modifier = Modifier.fillMaxWidth(),
                // Separa los extremos izquierdo y derecho
                horizontalArrangement = Arrangement.SpaceBetween,
                // Centra verticalmente ambos elementos
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Agrupa el título y el icono de sol
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Muestra "Condiciones hoy" desde strings.xml
                    Text(
                        // Texto leído de strings.xml
                        text = stringResource(R.string.detalle_condiciones_hoy),
                        // Estilo de título pequeño del tema
                        style = MaterialTheme.typography.titleSmall,
                        // Texto en negrita
                        fontWeight = FontWeight.Bold
                    )
                    // Espacio horizontal de 6 dp entre el texto y el icono de sol
                    Spacer(modifier = Modifier.width(6.dp))
                    // Icono de sol con descripción accesible desde strings.xml
                    Icon(
                        // Vector de sol
                        imageVector = Icons.Filled.WbSunny,
                        // Descripción leída desde strings.xml
                        contentDescription = stringResource(R.string.cd_sol),
                        // Color primario del tema
                        tint = MaterialTheme.colorScheme.primary,
                        // Tamaño de 18 dp
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Etiqueta con el estado general del clima (por ejemplo, "Óptimo")
                Surface(
                    // Forma de píldora circular
                    shape = CircleShape,
                    // Fondo de contenedor primario del tema
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    // Muestra el estado dinámico de las condiciones
                    Text(
                        // Estado del objeto condiciones
                        text = condiciones.estado,
                        // Color de contenido sobre primaryContainer
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        // Estilo de etiqueta pequeña del tema
                        style = MaterialTheme.typography.labelSmall,
                        // Texto en negrita
                        fontWeight = FontWeight.Bold,
                        // Relleno horizontal de 8 dp y vertical de 2 dp
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            // Espacio vertical de 12 dp antes de la primera fila de datos
            Spacer(modifier = Modifier.height(12.dp))

            // Primera fila con estado del agua a la izquierda y temperatura a la derecha
            Row(
                // Ocupa todo el ancho disponible
                modifier = Modifier.fillMaxWidth(),
                // Separa ambos bloques a los extremos
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Sub-fila con icono de agua y texto del estado del agua
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
                    // Espacio horizontal de 6 dp entre el icono y el texto
                    Spacer(modifier = Modifier.width(6.dp))
                    // Muestra el estado del agua
                    Text(
                        // Valor de agua del objeto condiciones
                        text = condiciones.agua,
                        // Estilo de cuerpo pequeño del tema
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Muestra la temperatura registrada
                Text(
                    // Valor de temperatura en negrita
                    text = condiciones.temperatura,
                    // Estilo de cuerpo pequeño del tema
                    style = MaterialTheme.typography.bodySmall,
                    // Texto en negrita
                    fontWeight = FontWeight.Bold
                )
            }

            // Espacio vertical de 8 dp entre filas
            Spacer(modifier = Modifier.height(8.dp))

            // Segunda fila con oleaje a la izquierda e índice UV a la derecha
            Row(
                // Ocupa todo el ancho disponible
                modifier = Modifier.fillMaxWidth(),
                // Separa ambos bloques a los extremos
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Sub-fila con icono de viento/oleaje y texto del oleaje
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icono decorativo de oleaje/aire
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
                    // Espacio horizontal de 6 dp entre el icono y el texto
                    Spacer(modifier = Modifier.width(6.dp))
                    // Muestra el estado del oleaje
                    Text(
                        // Valor de oleaje del objeto condiciones
                        text = condiciones.oleaje,
                        // Estilo de cuerpo pequeño del tema
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Muestra el índice de radiación ultravioleta
                Text(
                    // Valor de uv del objeto condiciones
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

// Declara el subcomponente de descripción expandible del lugar
@Composable
// Función Composable que muestra la descripción y permite alternar "Leer más / Leer menos"
fun DescripcionLugar(
    // Texto descriptivo completo del lugar
    descripcion: String,
    // Modificador opcional externo
    modifier: Modifier = Modifier
) {
    // Estado local que controla si el texto está expandido o contraído
    var expandido by remember { mutableStateOf(false) }

    // Columna que agrupa la descripción y el enlace de expansión
    Column(modifier = modifier.fillMaxWidth()) {
        // Muestra la descripción limitada a 3 líneas cuando no está expandida
        Text(
            // Texto descriptivo del lugar
            text = descripcion,
            // Estilo de cuerpo mediano del tema
            style = MaterialTheme.typography.bodyMedium,
            // Sin límite de líneas si está expandido, o 3 líneas si está contraído
            maxLines = if (expandido) Int.MAX_VALUE else 3,
            // Añade puntos suspensivos cuando está contraído y supera las 3 líneas
            overflow = TextOverflow.Ellipsis,
            // Color secundario sobre superficie
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            // Altura de línea cómoda para lectura
            lineHeight = 20.sp
        )

        // Espacio vertical de 6 dp antes del texto "Leer más / Leer menos"
        Spacer(modifier = Modifier.height(6.dp))

        // Texto interactivo para expandir o contraer la descripción
        Text(
            // Cambia entre "Leer menos" y "Leer más" según el estado expandido
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

// Declara el subcomponente que muestra los servicios disponibles y su contador
@Composable
// Función Composable que lista los servicios en un LazyRow
fun ServiciosLugar(
    // Lista de servicios asociados al lugar
    servicios: List<Servicio>,
    // Modificador opcional externo
    modifier: Modifier = Modifier
) {
    // Columna principal de la sección de servicios
    Column(modifier = modifier.fillMaxWidth()) {
        // Fila superior con el título de la sección y el contador de puntos/servicios
        Row(
            // Ocupa todo el ancho disponible
            modifier = Modifier.fillMaxWidth(),
            // Separa el título a la izquierda y el contador a la derecha
            horizontalArrangement = Arrangement.SpaceBetween,
            // Centra verticalmente ambos textos
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Muestra "Servicios e información relevante" desde strings.xml
            Text(
                // Texto leído de strings.xml
                text = stringResource(R.string.detalle_servicios_titulo),
                // Estilo de título mediano del tema
                style = MaterialTheme.typography.titleMedium,
                // Texto en negrita
                fontWeight = FontWeight.Bold
            )

            // Muestra el contador formateado "%1$d PUNTOS" desde strings.xml
            Text(
                // Pasa el tamaño entero de la lista servicios al formato %1$d
                text = stringResource(R.string.detalle_servicios_puntos, servicios.size),
                // Estilo de etiqueta pequeña del tema
                style = MaterialTheme.typography.labelSmall,
                // Color secundario sobre superficie
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                // Texto en negrita
                fontWeight = FontWeight.Bold
            )
        }

        // Espacio vertical de 12 dp antes de la lista horizontal de servicios
        Spacer(modifier = Modifier.height(12.dp))

        // Lista horizontal con desplazamiento para las tarjetas de servicio
        LazyRow(
            // Separación de 10 dp entre cada TarjetaServicio
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Crea una TarjetaServicio por cada elemento de la lista servicios
            items(servicios) { servicio ->
                // Pinta la tarjeta reutilizable del servicio
                TarjetaServicio(servicio = servicio)
            }
        }
    }
}
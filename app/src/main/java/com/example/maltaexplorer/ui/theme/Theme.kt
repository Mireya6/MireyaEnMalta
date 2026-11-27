package com.example.maltaexplorer.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.darkColorScheme

import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.Composable

// Define el esquema de colores para el modo claro asignando los tonos de Color.kt
private val LightColorScheme = lightColorScheme(
    // Color primario principal
    primary = AzulMaltaLight,
    // Color sobre primario
    onPrimary = OnAzulMaltaLight,
    // Contenedor primario
    primaryContainer = ContenedorAzulLight,
    // Contenido sobre contenedor primario
    onPrimaryContainer = OnContenedorAzulLight,
    // Color secundario
    secondary = TurquesaCominoLight,
    // Color sobre secundario
    onSecondary = OnTurquesaCominoLight,
    // Contenedor secundario
    secondaryContainer = ContenedorTurquesaLight,
    // Contenido sobre contenedor secundario
    onSecondaryContainer = OnContenedorTurquesaLight,
    // Color terciario
    tertiary = ArenaValetaLight,
    // Color sobre terciario
    onTertiary = OnArenaValetaLight,
    // Contenedor terciario
    tertiaryContainer = ContenedorArenaLight,
    // Contenido sobre contenedor terciario
    onTertiaryContainer = OnContenedorArenaLight,
    // Fondo general de pantalla
    background = FondoLight,
    // Texto sobre el fondo
    onBackground = OnFondoLight,
    // Superficie de tarjetas y contenedores
    surface = SuperficieLight,
    // Texto sobre superficies
    onSurface = OnSuperficieLight,
    // Superficie variante
    surfaceVariant = SuperficieVarianteLight,
    // Texto secundario sobre superficies
    onSurfaceVariant = OnSuperficieVarianteLight
)

// Define el esquema de colores para el modo oscuro asignando los tonos oscuros de Color.kt
private val DarkColorScheme = darkColorScheme(
    // Color primario principal en oscuro
    primary = AzulMaltaDark,
    // Color sobre primario en oscuro
    onPrimary = OnAzulMaltaDark,
    // Contenedor primario en oscuro
    primaryContainer = ContenedorAzulDark,
    // Contenido sobre contenedor primario en oscuro
    onPrimaryContainer = OnContenedorAzulDark,
    // Color secundario en oscuro
    secondary = TurquesaCominoDark,
    // Color sobre secundario en oscuro
    onSecondary = OnTurquesaCominoDark,
    // Contenedor secundario en oscuro
    secondaryContainer = ContenedorTurquesaDark,
    // Contenido sobre contenedor secundario en oscuro
    onSecondaryContainer = OnContenedorTurquesaDark,
    // Color terciario en oscuro
    tertiary = ArenaValetaDark,
    // Color sobre terciario en oscuro
    onTertiary = OnArenaValetaDark,
    // Contenedor terciario en oscuro
    tertiaryContainer = ContenedorArenaDark,
    // Contenido sobre contenedor terciario en oscuro
    onTertiaryContainer = OnContenedorArenaDark,
    // Fondo general de pantalla en oscuro
    background = FondoDark,
    // Texto sobre el fondo en oscuro
    onBackground = OnFondoDark,
    // Superficie de tarjetas en oscuro
    surface = SuperficieDark,
    // Texto sobre superficies en oscuro
    onSurface = OnSuperficieDark,
    // Superficie variante en oscuro
    surfaceVariant = SuperficieVarianteDark,
    // Texto secundario sobre superficies en oscuro
    onSurfaceVariant = OnSuperficieVarianteDark
)

// Declara el tema Composable personalizado que envuelve toda la aplicación
@Composable
fun MaltaExplorerTheme(
    // Detecta automáticamente si el dispositivo tiene activado el modo oscuro
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Contenido Composable hijo al que se aplicará el tema
    content: @Composable () -> Unit
) {
    // Selecciona la paleta oscura o clara según el parámetro darkTheme
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Aplica MaterialTheme con la paleta de colores y tipografía personalizadas
    MaterialTheme(
        // Pasa el esquema de colores elegido
        colorScheme = colorScheme,
        // Pasa la escala tipográfica definida en Type.kt
        typography = Typography,
        // Renderiza el contenido de la aplicación dentro del tema
        content = content
    )
}
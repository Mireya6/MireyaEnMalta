package com.example.maltaexplorer.ui.theme


import androidx.compose.material3.Typography

import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp

// Define la escala tipográfica personalizada utilizada por MaterialTheme en toda la app
val Typography = Typography(
    // Estilo para titulares principales de pantalla (por ejemplo, el nombre del lugar)
    headlineSmall = TextStyle(
        // Usa la familia sans-serif limpia y legible
        fontFamily = FontFamily.SansSerif,
        // Grosor negrita para destacar el título principal
        fontWeight = FontWeight.Bold,
        // Tamaño de letra de 24 sp
        fontSize = 24.sp,
        // Altura de línea de 32 sp
        lineHeight = 32.sp,
        // Espaciado estándar entre letras
        letterSpacing = 0.sp
    ),
    // Estilo para títulos de sección y barra superior
    titleMedium = TextStyle(
        // Familia sans-serif
        fontFamily = FontFamily.SansSerif,
        // Grosor seminegrita
        fontWeight = FontWeight.SemiBold,
        // Tamaño de letra de 18 sp
        fontSize = 18.sp,
        // Altura de línea de 24 sp
        lineHeight = 24.sp,
        // Espaciado ligero entre letras
        letterSpacing = 0.15.sp
    ),
    // Estilo para subtítulos dentro de tarjetas
    titleSmall = TextStyle(
        // Familia sans-serif
        fontFamily = FontFamily.SansSerif,
        // Grosor seminegrita
        fontWeight = FontWeight.SemiBold,
        // Tamaño de letra de 15 sp
        fontSize = 15.sp,
        // Altura de línea de 20 sp
        lineHeight = 20.sp,
        // Espaciado ligero entre letras
        letterSpacing = 0.1.sp
    ),
    // Estilo para párrafos de descripción general
    bodyMedium = TextStyle(
        // Familia sans-serif
        fontFamily = FontFamily.SansSerif,
        // Grosor normal para lectura cómoda
        fontWeight = FontWeight.Normal,
        // Tamaño de letra de 14 sp
        fontSize = 14.sp,
        // Altura de línea de 20 sp
        lineHeight = 20.sp,
        // Espaciado entre letras de 0.25 sp
        letterSpacing = 0.25.sp
    ),
    // Estilo para textos secundarios y detalles de clima o reseñas
    bodySmall = TextStyle(
        // Familia sans-serif
        fontFamily = FontFamily.SansSerif,
        // Grosor normal
        fontWeight = FontWeight.Normal,
        // Tamaño de letra de 12 sp
        fontSize = 12.sp,
        // Altura de línea de 16 sp
        lineHeight = 16.sp,
        // Espaciado entre letras de 0.4 sp
        letterSpacing = 0.4.sp
    ),
    // Estilo para botones de texto y etiquetas medianas
    labelMedium = TextStyle(
        // Familia sans-serif
        fontFamily = FontFamily.SansSerif,
        // Grosor seminegrita para elementos accionables
        fontWeight = FontWeight.SemiBold,
        // Tamaño de letra de 12 sp
        fontSize = 12.sp,
        // Altura de línea de 16 sp
        lineHeight = 16.sp,
        // Espaciado entre letras de 0.5 sp
        letterSpacing = 0.5.sp
    ),
    // Estilo para insignias, chips de categoría y textos muy compactos
    labelSmall = TextStyle(
        // Familia sans-serif
        fontFamily = FontFamily.SansSerif,
        // Grosor medio
        fontWeight = FontWeight.Medium,
        // Tamaño de letra de 11 sp
        fontSize = 11.sp,
        // Altura de línea de 14 sp
        lineHeight = 14.sp,
        // Espaciado entre letras de 0.5 sp
        letterSpacing = 0.5.sp
    )
)

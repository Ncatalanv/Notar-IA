package com.duoc.notar_ia.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

//Esquema de colores con los colores principales de NotariaX
private val AppColorScheme = darkColorScheme(
    background = FondoOscuro,
    surface = GrisMedio,  // superficies secundarias (diàlogos o tarjetas)
    onBackground = TextoClaro,  // texto sobre el fondo
    onSurface = TextoClaro,  //texto sobre las superficies
    primary = Dorado,
    onPrimary = FondoOscuro
)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

@Composable
fun NotariaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
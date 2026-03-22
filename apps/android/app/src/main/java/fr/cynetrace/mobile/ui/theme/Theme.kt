package fr.cynetrace.mobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = CynForest,
    secondary = Moss,
    tertiary = Sand,
    background = Mist,
    surface = Paper,
)

private val DarkColors = darkColorScheme(
    primary = Sand,
    secondary = Moss,
    tertiary = Bark,
    background = Night,
    surface = NightSurface,
)

@Composable
fun CyneTraceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content,
    )
}

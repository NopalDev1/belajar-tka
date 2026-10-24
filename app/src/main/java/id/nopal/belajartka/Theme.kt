package id.nopal.belajartka

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object C {
    val bg = Color(0xFF0F1222)
    val surface = Color(0xFF181C33)
    val surface2 = Color(0xFF222846)
    val line = Color(0xFF2E355A)
    val text = Color(0xFFF2F3FF)
    val muted = Color(0xFF9AA0C3)
    val violet = Color(0xFF7C5CFF)
    val pink = Color(0xFFFF6B9A)
    val good = Color(0xFF2ED3A0)
    val bad = Color(0xFFFF5C6C)
    val gold = Color(0xFFFFC94D)
    val hero = Brush.linearGradient(listOf(violet, pink))
}

private val scheme = darkColorScheme(
    primary = C.violet,
    onPrimary = Color.White,
    secondary = C.pink,
    background = C.bg,
    onBackground = C.text,
    surface = C.surface,
    onSurface = C.text,
    surfaceVariant = C.surface2,
    onSurfaceVariant = C.muted,
    outline = C.line,
    error = C.bad,
)

private val type = Typography(
    headlineLarge = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 36.sp),
    headlineMedium = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 30.sp),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 26.sp),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, lineHeight = 22.sp),
    bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
    labelMedium = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.4.sp),
)

@Composable
fun JalurTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = scheme, typography = type, content = content)
}

package id.nopal.belajartka

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * Calm, flat palette: warm off-white page, white cards without shadows, one deep blue accent.
 * Subject colors appear only as small accents. Names are kept from the first design so screens
 * did not need renaming: violet is the primary accent, pink the secondary (orange).
 */
object C {
    val bg = Color(0xFFF6F5F1)          // page
    val surface = Color(0xFFFFFFFF)     // cards
    val surface2 = Color(0xFFEEEDE8)    // inset fills, inactive chips
    val line = Color(0xFFE4E2DC)        // 1dp hairlines
    val text = Color(0xFF16181D)
    val muted = Color(0xFF666A72)
    val faded = Color(0xFFA3A6AC)
    val violet = Color(0xFF3247D1)      // primary accent: CTA, progress, selection
    val accentWash = Color(0xFFE6E9FB)
    val blue = Color(0xFF3247D1)
    val pink = Color(0xFFE8710A)
    val good = Color(0xFF15924A)
    val goodWash = Color(0xFFDDF5E6)
    val bad = Color(0xFFD62C2C)
    val gold = Color(0xFFE0A100)
    val ink = Color(0xFF16181D)
}

@OptIn(ExperimentalTextApi::class)
private fun jakarta(w: Int) = Font(
    R.font.jakarta, FontWeight(w),
    variationSettings = FontVariation.Settings(FontVariation.weight(w)),
)

@OptIn(ExperimentalTextApi::class)
/** Plus Jakarta Sans (OFL), an Indonesian-made typeface; variable weight 200 to 800. */
val Jakarta = FontFamily(jakarta(400), jakarta(500), jakarta(600), jakarta(700), jakarta(800))

private val scheme = lightColorScheme(
    primary = C.violet,
    onPrimary = Color.White,
    secondary = C.pink,
    background = C.bg,
    onBackground = C.text,
    surface = C.surface,
    onSurface = C.text,
    surfaceVariant = C.surface,
    onSurfaceVariant = C.muted,
    outline = C.line,
    error = C.bad,
)

private val type = Typography(
    headlineLarge = TextStyle(fontFamily = Jakarta, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 36.sp, letterSpacing = (-0.03).em),
    headlineMedium = TextStyle(fontFamily = Jakarta, fontSize = 23.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 29.sp, letterSpacing = (-0.02).em),
    titleLarge = TextStyle(fontFamily = Jakarta, fontSize = 19.sp, fontWeight = FontWeight.Bold, lineHeight = 25.sp, letterSpacing = (-0.01).em),
    titleMedium = TextStyle(fontFamily = Jakarta, fontSize = 16.sp, fontWeight = FontWeight.Bold, lineHeight = 22.sp),
    titleSmall = TextStyle(fontFamily = Jakarta, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, lineHeight = 20.sp),
    bodyLarge = TextStyle(fontFamily = Jakarta, fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = Jakarta, fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 20.sp),
    bodySmall = TextStyle(fontFamily = Jakarta, fontSize = 12.sp, fontWeight = FontWeight.Normal, lineHeight = 17.sp),
    labelLarge = TextStyle(fontFamily = Jakarta, fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
    labelMedium = TextStyle(fontFamily = Jakarta, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.02.em),
)

@Composable
fun JalurTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = scheme, typography = type) {
        androidx.compose.runtime.CompositionLocalProvider(
            androidx.compose.material3.LocalTextStyle provides type.bodyLarge,
            content = content,
        )
    }
}

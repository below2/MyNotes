package edu.towson.cosc435.group12.mynotes


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val primaryColor = Color(0xff9381ff) // topappbar
private val primaryVariantColor = Color(0xfff25757)
private val secondaryColor = Color(0xFF03DAC6)
private val secondaryVariantColor = Color(0xFF018786)
private val backgroundColor = Color(0xfff8f7ff) // background color
private val surfaceColor = Color(0xfff8f7ff) // card color
private val onPrimaryColor = Color(0xfff8f7ff)
private val onSecondaryColor = Color(0xFF000000)
private val onBackgroundColor = Color(0xFF000000)
private val onSurfaceColor = Color(0xFF000000)

@Composable
fun MyNotesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = myNotesColors,
        typography = myNotesTypography,
        content = content
    )
}

private val myNotesColors = darkColors(
    primary = primaryColor,
    primaryVariant = primaryVariantColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryVariantColor,
    background = backgroundColor,
    surface = surfaceColor,
    onPrimary = onPrimaryColor,
    onSecondary = onSecondaryColor,
    onBackground = onBackgroundColor,
    onSurface = onSurfaceColor
)

private val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_semibold, FontWeight.W600)
)

private val Domine = FontFamily(
    Font(R.font.domine_regular),
    Font(R.font.domine_bold, FontWeight.Bold)
)

val myNotesTypography = Typography(
    h4 = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = androidx.compose.ui.text.TextStyle(
        fontFamily = Domine,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontSize = 14.sp
    ),
    button = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = androidx.compose.ui.text.TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)
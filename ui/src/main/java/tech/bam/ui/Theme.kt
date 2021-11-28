package tech.bam.ui

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

val fontFamily = FontFamily(
    Font(R.font.eziear)
)

val typography = Typography(
    h1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 48.sp,
        color = Color(52, 52, 52)
    )
)
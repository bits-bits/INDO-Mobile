package com.bitsandbits.designsystem.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import indo.designsystem.generated.resources.Res
import indo.designsystem.generated.resources.urbanist_begular
import indo.designsystem.generated.resources.urbanist_medium
import indo.designsystem.generated.resources.urbanist_semibold

import org.jetbrains.compose.resources.Font


@Composable
fun createThemeTypography(): MyWeatherTypography {
    val urbanistFontFamily = FontFamily(
        Font(resource = Res.font.urbanist_begular, FontWeight.Normal),
        Font(resource = Res.font.urbanist_medium ,FontWeight.Medium),
        Font(resource = Res.font.urbanist_semibold, FontWeight.SemiBold)
    )

    return MyWeatherTypography(
        titleLarge = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        ),
        titleMedium = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        ),
        titleSmall = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        bodySmall = TextStyle(),
        labelLarge = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 64.sp
        ),
        labelMedium = TextStyle(
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        labelSmall = TextStyle(),
    )
}
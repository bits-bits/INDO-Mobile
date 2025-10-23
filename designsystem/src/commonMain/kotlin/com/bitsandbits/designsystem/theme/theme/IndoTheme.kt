package com.bitsandbits.designsystem.theme.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.bitsandbits.designsystem.theme.color.LocalMyWeatherColor
import com.bitsandbits.designsystem.theme.color.IndoColors
import com.bitsandbits.designsystem.theme.color.darkThemeColors
import com.bitsandbits.designsystem.theme.color.lightThemeColors
import com.bitsandbits.designsystem.theme.typography.LocalTypography
import com.bitsandbits.designsystem.theme.typography.MyWeatherTypography
import com.bitsandbits.designsystem.theme.typography.createThemeTypography


@Composable
fun IndoTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (isDarkTheme) darkThemeColors else lightThemeColors
    val typography = createThemeTypography()


    CompositionLocalProvider(
        LocalMyWeatherColor provides colorScheme,
        LocalTypography provides typography
    ) {
        content()

    }
}

object Theme {
    val color: IndoColors
        @Composable @ReadOnlyComposable get() = LocalMyWeatherColor.current

    val textStyle: MyWeatherTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

}


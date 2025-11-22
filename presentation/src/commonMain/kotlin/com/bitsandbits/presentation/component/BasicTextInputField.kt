package com.bitsandbits.presentation.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitsandbits.designsystem.theme.theme.Theme

@Composable
fun BasicTextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    startIconPainter: Painter?,
    endIconPainter: Painter?,
    modifier: Modifier = Modifier,
    onClickEndIcon: () -> Unit = { },
    onClickInputTextField: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    borderBrushColors: Brush? = Brush.linearGradient(
        colors = listOf(
            Color(0xFF663EF6),
            Color(0xFFB7A4FB)
        )
    ),
    errorBorderBrush: Brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF663EF6),
            Color(0xFFB7A4FB)
        )
    ),
    isError: Boolean = false,
    iconColorInFocus: Color = Theme.color.onPrimary,
    iconColorNotFocus: Color = Theme.color.onPrimaryContainer,
    cursorColor: Color = Theme.color.onPrimary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    letterSpacing: Int = 0,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        autoCorrect = false,
        keyboardType = KeyboardType.Password
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value))
    }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(value) {
        if (textFieldValue.text != value) {
            textFieldValue = TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        }
    }

    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    val borderModifier = when {
        isError -> Modifier.border(
            width = 2.dp,
            brush = errorBorderBrush,
            shape = RoundedCornerShape(8.dp)
        )

        isFocused && borderBrushColors != null -> Modifier.border(
            width = 1.dp,
            brush = borderBrushColors,
            shape = RoundedCornerShape(8.dp)
        )

        else -> Modifier
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            textFieldValue = newValue
            onValueChange(newValue.text)
        },
        textStyle = Theme.textStyle.labelSmall.copy(
            color = if (isFocused || textFieldValue.text.isNotEmpty())
                Theme.color.secondary
            else
                Theme.color.onPrimary,
            letterSpacing = letterSpacing.sp
        ),
        interactionSource = interactionSource,
        singleLine = true,
        visualTransformation = visualTransformation,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { }
            .then(borderModifier)
            .background(Theme.color.primaryContainer, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 14.dp),

        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (startIconPainter != null) {
                    Icon(
                        painter = startIconPainter,
                        contentDescription = null,
                        tint = if (isFocused || value.isNotEmpty())
                            iconColorInFocus
                        else {
                            iconColorNotFocus
                        },

                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(20.dp)
                    )
                }

                Box(
                    modifier =
                        Modifier
                            .weight(1f)
                            .clickable {
                                focusManager.clearFocus(force = true)
                                focusRequester.requestFocus()
                                onClickInputTextField()
                            }
                ) {
                    if (textFieldValue.text.isEmpty()) {
                        Text(
                            text = hintText,
                            style = Theme.textStyle.labelSmall,
                            color = Theme.color.primary
                        )
                    }
                    innerTextField()
                }

                if (endIconPainter != null && value.isNotEmpty()) {
                    Crossfade(
                        targetState = endIconPainter,
                        label = ""
                    ) { icon ->
                        Icon(
                            painter = icon,
                            contentDescription = null,
                            tint = if (isFocused || value.isNotEmpty())
                                iconColorInFocus
                            else {
                                iconColorNotFocus
                            },
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(20.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) { onClickEndIcon() }
                        )
                    }
                }
            }
        },
        cursorBrush = SolidColor(cursorColor),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
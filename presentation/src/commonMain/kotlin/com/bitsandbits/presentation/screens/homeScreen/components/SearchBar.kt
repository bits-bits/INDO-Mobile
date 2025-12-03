package com.bitsandbits.presentation.screens.homeScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.bitsandbits.presentation.component.BasicTextField
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.ic_clear
import indo.presentation.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchBar(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onClearQueryClicked: () -> Unit,
    onFocusChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
    isFocused: Boolean = false,
    focusRequester: FocusRequester = FocusRequester()
) {
    val focusManager = LocalFocusManager.current
    if (isFocused.not())
        focusManager.clearFocus()

    BasicTextField(
        value = value,
        onValueChanged = onValueChange,
        leadingIcon = painterResource(Res.drawable.ic_search),
        hint = hint,
        showTrailingDivider = false,
        trailingIcon = if (value.isNotBlank()) painterResource(Res.drawable.ic_clear) else null,
        onTrailingIconClick = onClearQueryClicked,
        onFocusChanged = {
            println("TAG FOCUS CHANGED: $it")
            onFocusChange(it) },
        modifier = modifier,
        focusRequester = focusRequester
    )
}
package com.bitsandbits.presentation.screens.login.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bitsandbits.presentation.component.BasicTextField
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.hide_eye
import indo.presentation.generated.resources.ic_clear
import indo.presentation.generated.resources.ic_profile_placeholder
import indo.presentation.generated.resources.mail
import indo.presentation.generated.resources.view_eye
import org.jetbrains.compose.resources.painterResource

@Composable
fun CredentialsTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
    isFocused: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    isPassword: Boolean = false
) {
    val focusManager = LocalFocusManager.current
    if (isFocused.not())
        focusManager.clearFocus()

    val startIcon = if (isPassword) painterResource(Res.drawable.ic_profile_placeholder) else painterResource(Res.drawable.mail)
    var showPassword by remember { mutableStateOf(false) }
    val trailingIcon = if(showPassword) painterResource(Res.drawable.hide_eye) else painterResource(Res.drawable.view_eye)

    BasicTextField(
        value = value,
        onValueChanged = onValueChange,
        leadingIcon = startIcon,
        hint = hint,
        showTrailingDivider = false,
        trailingIcon = if (isPassword) trailingIcon else null,
        onTrailingIconClick = { showPassword = !showPassword },
        onFocusChanged = {
            println("TAG FOCUS CHANGED: $it")
            onFocusChange(it) },
        modifier = modifier,
        focusRequester = focusRequester,
        visualTransformation = if (isPassword && showPassword.not()) PasswordVisualTransformation() else VisualTransformation.None
    )
}


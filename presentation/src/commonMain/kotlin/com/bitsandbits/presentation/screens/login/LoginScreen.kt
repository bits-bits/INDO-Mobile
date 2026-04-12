package com.bitsandbits.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.utils.EffectHandler
import com.bitsandbits.presentation.component.ImageViewer
import com.bitsandbits.presentation.component.IndoImageSource
import com.bitsandbits.presentation.navigation.Destinations
import com.bitsandbits.presentation.navigation.LocalNavController
import com.bitsandbits.presentation.screens.login.component.CredentialsTextField
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.alex_uni
import kotlinx.coroutines.flow.SharedFlow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = koinViewModel()) {
    val state by loginViewModel.state.collectAsStateWithLifecycle()
    val effect = loginViewModel.effect
    LoginEffectsHandler(effect)
    LoginScreenContent(state = state, interactions = loginViewModel as LoginInteractionListener)
}

@Composable
fun LoginScreenContent(state: LoginUiState, interactions: LoginInteractionListener) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Theme.color.onSecondary)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(contentAlignment = Alignment.Center){
            Row(modifier = Modifier.fillMaxWidth().height(250.dp).clip(RoundedCornerShape(bottomStart = 22.dp, bottomEnd = 22.dp)).background(Theme.color.primary.copy(alpha = 0.7f))) {  }
            ImageViewer(
                IndoImageSource.PainterSource(painterResource(Res.drawable.alex_uni)),
                modifier = Modifier.statusBarsPadding().width(180.dp).height(180.dp).clip(RoundedCornerShape(12))
            )
        }

        CredentialsTextField(
            value = state.username,
            hint = "Username",
            isFocused = true,
            onValueChange = { username -> interactions.onUserNameChanged(username) },
            modifier = Modifier.padding(bottom = 8.dp, top = 16.dp).padding(horizontal = 16.dp)
        )
        CredentialsTextField(
            value = state.password,
            hint = "password",
            isPassword = true,
            isFocused = true,
            onValueChange = { password -> interactions.onPasswordChanged(password) },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.weight(1f))
        if (state.errorMessage != null) {
            Text(
                text = state.errorMessage,
                style = Theme.textStyle.bodyMedium,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp).fillMaxWidth().navigationBarsPadding()
                .height(40.dp).clip(RoundedCornerShape(18f)).background(color = if (state.loginButtonEnabled) Theme.color.primary else Color.Gray.copy(alpha = 0.5f))
                .clickable(onClick = { if (state.loginButtonEnabled) interactions.onLoginClicked() }),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Login", style = Theme.textStyle.bodyMedium, color = Color.White)
        }
    }
}

@Composable
private fun LoginEffectsHandler(effect: SharedFlow<LoginEffect>) {
    val navController = LocalNavController.current
    EffectHandler(effect) { effect ->
        when (effect) {
            LoginEffect.NavigateToHome -> {
                navController.navigate(Destinations.HomeScreenRoute)
            }

            LoginEffect.ShowCredentialsErrorSnackBar -> {
            }

            LoginEffect.ShowNetworkErrorSnackBar -> {}
            LoginEffect.ShowSuccessSnackBar -> {}
        }
    }
}

fun Modifier.removeWidthPaddingFromParent(parentPadding: Dp) = layout { measurable, constraints ->
    val newMaxWidth = constraints.maxWidth + 2 * parentPadding.roundToPx()

    val newConstraints = constraints.copy(maxWidth = newMaxWidth)

    val placeable = measurable.measure(newConstraints)

    layout(constraints.maxWidth, placeable.height) {
        placeable.place(-parentPadding.roundToPx(), 0)
    }
}
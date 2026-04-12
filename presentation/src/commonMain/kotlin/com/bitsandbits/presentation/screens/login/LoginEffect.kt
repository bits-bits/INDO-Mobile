package com.bitsandbits.presentation.screens.login

sealed interface LoginEffect {
    data object ShowSuccessSnackBar : LoginEffect
    data object ShowCredentialsErrorSnackBar : LoginEffect
    data object ShowNetworkErrorSnackBar : LoginEffect
    data object NavigateToHome : LoginEffect
}
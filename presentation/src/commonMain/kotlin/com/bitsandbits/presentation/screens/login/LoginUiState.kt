package com.bitsandbits.presentation.screens.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loginButtonEnabled: Boolean = false,
)

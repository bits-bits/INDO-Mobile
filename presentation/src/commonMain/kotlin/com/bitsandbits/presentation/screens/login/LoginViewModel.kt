package com.bitsandbits.presentation.screens.login

import com.bitsandbits.presentation.Base.BaseViewModel
import com.bitsandbits.repository.UserRepository

class LoginViewModel(
    private val userRepository: UserRepository
) : BaseViewModel<LoginUiState, LoginEffect>(LoginUiState()), LoginInteractionListener {

    override fun onUserNameChanged(newUserName: String) {
        updateState { it.copy(username = newUserName) }
        toggleLoginButtonVisibility()
    }

    override fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword) }
        toggleLoginButtonVisibility()
    }

    private fun toggleLoginButtonVisibility() {
        val isEmailValid = validateEmail(state.value.username)
        val isPasswordValid = validatePassword(state.value.password)
        updateState { it.copy(loginButtonEnabled = isEmailValid && isPasswordValid) }
    }

    override fun onLoginClicked() {
        tryToExecute(
            function = {
                userRepository.login(username = state.value.username, password = state.value.password)
            },
            onSuccess = {
                updateState { it.copy(errorMessage = null) }
                emitNewEffect(LoginEffect.NavigateToHome)
            },
            onError = {
                // we should map the error
                updateState { it.copy(errorMessage = "Invalid credentials") }
                emitNewEffect(LoginEffect.ShowCredentialsErrorSnackBar)
            }
        )
    }


}

private fun validateEmail(email: String): Boolean {
    // Simple email validation regex
    val emailRegex = "^[A-Za-z0-9._%+-]+@alexu\\.edu\\.eg$"
    return email.matches(emailRegex.toRegex())
}

private fun validatePassword(password: String): Boolean {
    // Simple password validation (at least 6 characters)
    return password.length >= 6
}
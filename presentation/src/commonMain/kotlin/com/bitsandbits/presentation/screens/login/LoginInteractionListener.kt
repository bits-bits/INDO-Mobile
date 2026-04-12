package com.bitsandbits.presentation.screens.login

interface LoginInteractionListener {
    fun onLoginClicked()
    fun onUserNameChanged(newUserName: String)
    fun onPasswordChanged(newPassword: String)
}
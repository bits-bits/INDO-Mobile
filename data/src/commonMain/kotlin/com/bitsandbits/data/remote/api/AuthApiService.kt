package com.bitsandbits.data.remote.api

import io.ktor.client.HttpClient

class AuthApiService(private val client: HttpClient = PlainNetworkClient.client) {
    suspend fun refresh(refreshToken: String?): TokenResponse {
        // TODO: perform real endpoint, if unauth -> refresh token is expired -> fall back to login screen by throwing exception
        return TokenResponse(accessToken = "acc",refreshToken = "ref")
    }
}

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
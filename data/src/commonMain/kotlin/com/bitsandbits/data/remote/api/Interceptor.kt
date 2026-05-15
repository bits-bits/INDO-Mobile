package com.bitsandbits.data.remote.api

import com.bitsandbits.data.repository.TokenStorageRepo
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode

//class AuthInterceptor(
//    private val tokenStorage: TokenStorageRepo,
//    private val authApi: AuthApiService
//)
//{
//
//    fun plugin() = createClientPlugin("AuthInterceptor") {
//
//        onRequest { request, _ ->
//
//            val token = tokenStorage.getAccessToken()
//
//            if (token != null) {
//
//                request.headers.append(
//
//                    HttpHeaders.Authorization,
//
//                    "Bearer $token"
//
//                )
//
//            }
//
//        }
//
//        onResponse { response ->
//
//            if (response.status == HttpStatusCode.Unauthorized) {
//
//                updateTokens()
//                val newAccessToken = tokenStorage.getAccessToken()
//                // retry request
//                if (newAccessToken != null) {
//
//                    // retry logic is handled outside (explained below)
//
//                }
//
//            }
//
//        }
//
//    }
//
//    private suspend fun updateTokens() {
//        val refreshToken = tokenStorage.getRefreshToken()
//        val response = authApi.refresh(refreshToken)
//        tokenStorage.saveAccessToken(response.accessToken)
//        tokenStorage.saveRefreshToken(response.refreshToken)
//    }
//
//}

class AuthInterceptor(
    private val tokenStorage: TokenStorageRepo,
    private val authApi: AuthApiService
) {
    fun plugin() = createClientPlugin("AuthInterceptor") {

        // Attach access token to every request
        onRequest { request, _ ->
            val token = tokenStorage.getAccessToken()
            if (token != null) {
                request.headers[HttpHeaders.Authorization] = "Bearer $token"
            }
        }

        // Intercept at the send level — allows retry
        on(Send) { request ->
            val originalCall = proceed(request)

            if (originalCall.response.status == HttpStatusCode.Unauthorized) {
                try {
                    // Refresh tokens
                    val refreshToken = tokenStorage.getRefreshToken()
                    val newTokens = authApi.refresh(refreshToken)
                    tokenStorage.saveAccessToken(newTokens.accessToken)
                    tokenStorage.saveRefreshToken(newTokens.refreshToken)

                    // Retry original request with new token
                    request.headers[HttpHeaders.Authorization] = "Bearer ${newTokens.accessToken}"
                    proceed(request)
                } catch (e: Exception) {
                    // Refresh token expired → force logout
                    tokenStorage.clear()
                    throw SessionExpiredException("Session expired, please log in again")
                }
            } else {
                originalCall
            }
        }
    }
}

class SessionExpiredException(message: String) : Exception(message)
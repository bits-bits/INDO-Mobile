package com.bitsandbits.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    private const val BASE_URL = "http://localhost:8080"
    private const val AUTH_TOKEN = "123456"

    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(Auth) {
                basic {
                    credentials {
                        BasicAuthCredentials(username = AUTH_TOKEN, password = "")
                    }
                    sendWithoutRequest { true }
                }
            }

            defaultRequest {
                url(BASE_URL)
            }
        }
    }
}
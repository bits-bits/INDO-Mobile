package com.bitsandbits.data.remote.api

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.*
import io.ktor.http.URLProtocol

expect fun httpClientEngine(): HttpClientEngine

object NetworkClient {
//    private const val BASE_URL = "http://10.0.2.2:8080"   // android emulator
    private const val BASE_URL = "http://localhost:8080"    // ios emulator
    private const val USERNAME = "user"
    private const val PASSWORD = "123456"

    val client = HttpClient(httpClientEngine()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = USERNAME, password = PASSWORD)
                }
                sendWithoutRequest { true }
            }
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = "localhost"
                port = 8080
            }
            headers.append("Accept", "application/json")
        }
    }
}
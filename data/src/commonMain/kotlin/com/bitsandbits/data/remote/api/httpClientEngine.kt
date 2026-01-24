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
//                host = "localhost"      // ios
//                host = "10.0.2.2"       // android
//                host = "10.176.4.132"       // android
//                host = "172.20.10.2"       // android
//                host = "10.220.39.86"       // android
//                host = "192.168.1.3"        // home wifi
//                host = "192.168.1.4"        // home wifi
//                host = "10.30.52.188"        // home wifi
                host = "172.20.10.2"        // home wifi
                port = 8080
            }
            headers.append("Accept", "application/json")
        }
    }
}
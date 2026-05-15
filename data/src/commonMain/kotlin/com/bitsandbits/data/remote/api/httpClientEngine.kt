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
import com.bitsandbits.config.BuildKonfig

expect fun httpClientEngine(): HttpClientEngine

object NetworkClient {
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

//        install(authInterceptor.plugin())

        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(username = BuildKonfig.USERNAME, password = BuildKonfig.PASSWORD)
                }
                sendWithoutRequest { true }
            }
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = BuildKonfig.HOST      // home wifi
                port = BuildKonfig.PORT
            }
            headers.append("Accept", "application/json")
        }
    }
}


object PlainNetworkClient {
    val client = HttpClient(httpClientEngine()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(Logging) { level = LogLevel.ALL }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BuildKonfig.HOST
            }
            headers.append("Accept", "application/json")
        }
    }
}
package com.bitsandbits.data

import com.bitsandbits.data.remote.api.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ImageDownloader(private val client: HttpClient = NetworkClient.client) {
    suspend fun downloadBytes(url: String): ByteArray {
        val imageUrl = "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"
        val response = client.get(imageUrl)
        val byteArray = response.body<ByteArray>()
        return byteArray
    }
}

suspend fun downloadBytes(url: String): ByteArray {
    val client = NetworkClient.client
    val imageUrl = "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"
    val response = client.get(imageUrl)
    val byteArray = response.body<ByteArray>()
    return byteArray
}
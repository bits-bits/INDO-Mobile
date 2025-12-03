package com.bitsandbits.data.remote.mapper

import com.bitsandbits.data.remote.dto.FloorDetailsResponse
import com.bitsandbits.data.remote.dto.FloorResponse
import com.bitsandbits.entity.Floor
import com.bitsandbits.entity.FloorDetails

fun FloorResponse.toDomain(): Floor{
    return Floor(
        id = this.id,
        number = this.number,
        imageUrl = this.imageUrl
    )
}

fun FloorDetailsResponse.toDomain(): FloorDetails {
    println("TAG BOB, IN DATA MAPPER: floor details response: $this")
    val x =  FloorDetails(
        id = this.id,
        number = this.number,
        imageUrl = this.imageUrl,
        locations = this.locationBasicDetails.map { it.toDomain() }
    )
    println("TAG BOB, IN DATA MAPPER: floor details x mapping: $x")
    return x
}
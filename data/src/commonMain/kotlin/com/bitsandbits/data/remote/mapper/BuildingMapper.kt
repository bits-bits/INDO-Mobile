package com.bitsandbits.data.remote.mapper

import com.bitsandbits.data.remote.dto.BuildingDetailsResponse
import com.bitsandbits.data.remote.dto.BuildingResponse
import com.bitsandbits.entity.Building
import com.bitsandbits.entity.BuildingDetails

fun BuildingResponse.toDomain(): Building{
    return Building(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
    )
}

fun BuildingDetailsResponse.toDomain(): BuildingDetails{
    return BuildingDetails(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        floors = this.floorsBasicDetailResponses.map { it.toDomain() },
        longitude = null,
        latitude = null
    )
}
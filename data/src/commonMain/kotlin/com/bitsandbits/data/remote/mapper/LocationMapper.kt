package com.bitsandbits.data.remote.mapper

import com.bitsandbits.data.remote.dto.LocationDetailsResponse
import com.bitsandbits.data.remote.dto.LocationResponse
import com.bitsandbits.entity.Location

fun LocationDetailsResponse.toDomain(): Location{
    return Location(
        id = this.id,
        name = this.name,
        aliasName = this.aliasName,
        floorNumber = this.floorNumber,
        buildingName = this.buildingName
    )
}

fun LocationResponse.toDomain(): Location {
    return Location(
        id = this.id,
        name = this.name ?:"",
        aliasName = this.aliasName,
        floorNumber = 0,
        buildingName = ""
    )
}
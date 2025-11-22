package com.bitsandbits.data.remote.mapper

import com.bitsandbits.data.remote.dto.LocationResponseItem
import com.bitsandbits.entity.Location

fun LocationResponseItem.toDomain(): Location{
    return Location(
        id = this.id,
        name = this.name,
        aliasName = this.aliasName,
        floorNumber = this.floorNumber,
        buildingName = this.buildingName
    )
}
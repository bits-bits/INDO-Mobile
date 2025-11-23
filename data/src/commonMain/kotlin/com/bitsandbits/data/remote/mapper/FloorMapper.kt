package com.bitsandbits.data.remote.mapper

import com.bitsandbits.data.remote.dto.FloorsBasicDetailsResponse
import com.bitsandbits.entity.Floor

fun FloorsBasicDetailsResponse.toDomain(): Floor{
    return Floor(
        id = this.id,
        number = this.number,
        imageUrl = "https://i.ibb.co/qLK13KMB/floor-3.png"
    )
}
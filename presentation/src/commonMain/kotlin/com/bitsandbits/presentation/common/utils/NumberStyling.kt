package com.bitsandbits.presentation.common.utils

fun Int.toFloorName(): String{
    return when(this){
        -1 -> "Basement"
        0 -> "Ground Floor"
        1 -> "1st Floor"
        2 -> "2nd Floor"
        3 -> "3rd Floor"
        else -> "${this}th Floor"
    }
}
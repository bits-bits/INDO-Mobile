package org.bitsandbits.indo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
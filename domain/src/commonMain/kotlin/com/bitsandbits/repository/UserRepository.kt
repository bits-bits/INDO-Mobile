package com.bitsandbits.repository

interface UserRepository {
    fun login(username: String, password: String)
}
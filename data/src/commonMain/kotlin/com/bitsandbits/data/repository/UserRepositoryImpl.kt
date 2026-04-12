package com.bitsandbits.data.repository

import com.bitsandbits.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun login(username: String, password: String) {
        if (username == "admin@alexu.edu.eg" && password == "password") {
            return
        }
        throw Exception("Invalid credentials")
        // Implement login logic here
    }
}
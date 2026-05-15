package com.bitsandbits.data.repository

import android.content.SharedPreferences

class TokenStorageRepoRepoImpl(private val prefs: SharedPreferences): TokenStorageRepo {
    override suspend fun saveAccessToken(token: String) {
        prefs.edit()
            .putString("access_token", token)
            .apply()
    }

    override suspend fun saveRefreshToken(token: String) {
        prefs.edit()
            .putString("refresh_token", token)
            .apply()
    }

    override suspend fun getAccessToken(): String? {
        return prefs.getString("access_token", null)
    }

    override suspend fun getRefreshToken(): String? {
        return prefs.getString("refresh_token", null)
    }

    override suspend fun clear() {
        prefs.edit()
            .clear()
            .apply()
    }

}
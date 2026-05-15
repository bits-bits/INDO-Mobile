package com.bitsandbits.data.repository

import kotlinx.cinterop.*
import platform.CoreFoundation.*
import platform.Foundation.*
import platform.Security.*

class TokenStorageRepoImpl : TokenStorageRepo {

    private val service = "auth_tokens"

    @OptIn(BetaInteropApi::class)
    private fun String.toNSData(): NSData =
        NSString.create(string = this)
            .dataUsingEncoding(NSUTF8StringEncoding)!!

    // -------------------------
    // SAVE
    // -------------------------

    @OptIn(ExperimentalForeignApi::class)
    private fun save(key: String, value: String) {
        val data = value.toNSData()

        // Delete query — no kSecValueData
        val deleteQuery = mapOf<Any?, Any?>(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
            kSecAttrAccount to key,
        )
        SecItemDelete(deleteQuery as CFDictionaryRef)

        // Add query — includes kSecValueData
        val addQuery = mapOf<Any?, Any?>(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
            kSecAttrAccount to key,
            kSecValueData to data,
        )
        val status = SecItemAdd(addQuery as CFDictionaryRef, null)
        check(status == errSecSuccess) { "Keychain save failed for key=$key, status=$status" }
    }

    // -------------------------
    // READ
    // -------------------------

    @OptIn(ExperimentalForeignApi::class)
    private fun read(key: String): String? {
        val query = mapOf<Any?, Any?>(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
            kSecAttrAccount to key,
            kSecReturnData to true,
            kSecMatchLimit to kSecMatchLimitOne,
        )

        return memScoped {
            val resultRef = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(
                CFBridgingRetain(query) as CFDictionaryRef,
                resultRef.ptr
            )
            if (status == errSecSuccess) {
                val nsData = CFBridgingRelease(resultRef.value) as? NSData
                nsData?.let { NSString.create(it, NSUTF8StringEncoding) as? String }
            } else {
                null
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun clear() {
        val query = mapOf<Any?, Any?>(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to service,
        )
        SecItemDelete(CFBridgingRetain(query) as CFDictionaryRef)
    }

    // -------------------------
    // API
    // -------------------------

    override suspend fun saveAccessToken(token: String) = save("access_token", token)
    override suspend fun saveRefreshToken(token: String) = save("refresh_token", token)
    override suspend fun getAccessToken(): String? = read("access_token")
    override suspend fun getRefreshToken(): String? = read("refresh_token")
}
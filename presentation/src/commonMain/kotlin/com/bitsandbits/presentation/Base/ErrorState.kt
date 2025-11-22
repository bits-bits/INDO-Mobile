package com.bitsandbits.presentation.Base

open class ErrorState(val message: String, val errorImageId: Int? = null)
class NetworkError(message: String) : ErrorState(message)
class NotFoundError(message: String) : ErrorState(message)
class AlreadyExistsError(message: String) : ErrorState(message)
class ServerError(message: String) : ErrorState(message)

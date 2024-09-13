package com.module.core

import util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import util.NetworkError

class AuthorizationClient(
    private val httpClient: HttpClient
) {

    // 1. Регистрация: создание кода
    suspend fun registerCreateCode(phone: String): Result<String, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "https://delta.online/api/auth/phone-create-code"
            ) {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone, "status" to "register"))
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> Result.Success("Code sent successfully")
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    // 2. Регистрация: проверка кода и завершение
    suspend fun registerVerifyCode(phone: String, code: String, name: String, token: String): Result<String, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "https://delta.online/api/auth/phone-verify-code-register"
            ) {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone, "code" to code, "name" to name, "token" to token))
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> Result.Success("Registration successful")
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    // 3. Авторизация: создание кода
    suspend fun loginCreateCode(phone: String): Result<String, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "https://delta.online/api/auth/phone-create-code"
            ) {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone, "status" to "login"))
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> Result.Success("Code sent successfully")
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    // 4. Авторизация: проверка кода
    suspend fun loginVerifyCode(phone: String, code: String, token: String): Result<String, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "https://delta.online/api/auth/phone-verify-code"
            ) {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone, "code" to code, "token" to token))
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> Result.Success("Login successful")
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    // 5. Выход
    suspend fun logout(): Result<String, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = "https://delta.online/api/auth/logout"
            )
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> Result.Success("Logout successful")
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}
package com.project.network.authorization_network

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import util.NetworkError
import util.Result

class AuthorizationClient(
    private val httpClient: HttpClient
) {
    var userToken: String? = null  // Переменная для хранения токена

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
            in 200..299 -> {
                // Извлекаем тело ответа
                val responseBody = response.bodyAsText()

                println("${responseBody}")
                val json = Json.parseToJsonElement(responseBody).jsonObject

                // Пытаемся получить токен пользователя
                userToken = json["access_token"]?.jsonPrimitive?.contentOrNull

                if (userToken != null) {
                    println("User token1: $userToken")  // Выводим токен для отладки
                    Result.Success("Login successful")  // Возвращаем успешный результат
                } else {
                    println("user token: null")
                    Result.Error(NetworkError.UNKNOWN)  // Если токен не найден
                }
                return Result.Success("Registration successful")
            }

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
            in 200..299 -> {
                // Извлекаем тело ответа
                val responseBody = response.bodyAsText()

                println("${responseBody}")
                val json = Json.parseToJsonElement(responseBody).jsonObject

                // Пытаемся получить токен пользователя
                userToken = json["access_token"]?.jsonPrimitive?.contentOrNull

                if (userToken != null) {
                    println("User token1: $userToken")  // Выводим токен для отладки
                    Result.Success("Login successful")  // Возвращаем успешный результат
                } else {
                    println("user token: null")
                    Result.Error(NetworkError.UNKNOWN)  // Если токен не найден
                }
                return Result.Success(userToken?:"")
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getToken(phone:String,name:String,code:String,token:String):String  = try {
        val response =
            httpClient.post(
                urlString = "https://delta.online/api/auth/phone-verify-code-register"
            ) {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone, "code" to code, "name" to name, "token" to token))
            }
        val responseBody = response.bodyAsText()

        println("${responseBody}")
        val json = Json.parseToJsonElement(responseBody).jsonObject
        println("/////////////))))${json["access_token"]?.jsonPrimitive?.contentOrNull?:""}")
        // Пытаемся получить токен пользователя
      json["access_token"]?.jsonPrimitive?.contentOrNull?:""

    } catch (e: UnresolvedAddressException) {
        println("//////+++++!!!!!${ e.message.toString()}")
        Result.Error(NetworkError.NO_INTERNET).error.name
    } catch (e: SerializationException) {
        println("//////+++++!!!!!${ e.message.toString()}")
         Result.Error(NetworkError.SERIALIZATION).error.name
    }catch (e:Exception) {
        println("//////+++++!!!!!${ e.message.toString()}")
        e.message.toString()
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
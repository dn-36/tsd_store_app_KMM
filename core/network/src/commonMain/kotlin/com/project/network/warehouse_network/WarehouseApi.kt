package com.project.network.warehouse_network

import com.project.network.httpClientEngine
import com.project.network.notes_network.NotesApi
import com.project.network.notes_network.model.User
import com.project.network.organizations_network.OrganizationsApi
import com.project.network.organizations_network.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

object WarehouseApi {

    var token: String = ""

    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Игнорировать неизвестные поля
                isLenient = true // Быть гибким с форматом JSON
                coerceInputValues = true // Принудительно преобразовывает несовпадающие типы, например, null к правильному типу

            })
        }
        install(Logging) {
            level = LogLevel.BODY // Включить логирование для отладки
        }
        defaultRequest {
            header("Authorization", "Bearer $token")
        }
    }
   /* object StringOrIntSerializer : KSerializer<String> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("StringOrInt", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): String {
            return try {
                decoder.decodeString()  // Попробуем десериализовать как строку
            } catch (e: Exception) {
                decoder.decodeInt().toString()  // Если это число, преобразуем его в строку
            }
        }

        override fun serialize(encoder: Encoder, value: String) {
            encoder.encodeString(value)  // Всегда сериализуем как строку
        }
    }*/

   /* // получение всех локаций
    suspend fun getLocations(): List<LocationResponse> {
        return try {
            val response = client.get("https://delta.online/api/local") {
                // здесь можно добавить заголовки или другие параметры запроса
            }
            println(" ////////////////////++++++++++")
            println("лоКАЦИЯ ${response}")
            println(" ////////////////////++++++++++")

            // Десериализуем ответ в список объектов LocationResponse
            val responseBody = response.bodyAsText()
            Json { ignoreUnknownKeys = true }.decodeFromString<List<LocationResponse>>(responseBody)
        } catch (e: Exception) {
            println("Ошибка при получении локаций: ${e.message}")
            // Возвращаем пустой список в случае ошибки
            emptyList()
        }
    }*/

    object PointSerializer : KSerializer<List<Double>> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("point", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: List<Double>) {
            encoder.encodeString(value.joinToString(prefix = "[", postfix = "]"))
        }

        override fun deserialize(decoder: Decoder): List<Double> {
            val pointString = decoder.decodeString()
            return pointString
                .removeSurrounding("[", "]")  // Убираем квадратные скобки
                .split(",")                    // Разделяем строку по запятой
                .map { it.toDouble() }          // Преобразуем элементы в Double
        }
    }
   suspend fun getLocations(): List<ResponseItem> {
       val response = client.get("https://delta.online/api/local")
       println(" ////////////////////++++++++++")
       println(" ${response}")
       println(" ////////////////////++++++++++")
       return response.body<List<ResponseItem>>()
   }

}
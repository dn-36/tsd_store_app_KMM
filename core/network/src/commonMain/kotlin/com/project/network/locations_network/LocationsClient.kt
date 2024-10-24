package com.project.network.locations_network

import com.project.network.ConstData
import com.project.network.httpClientEngine
import com.project.network.locations_network.model.ResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

class LocationsClient {

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
            header("Authorization", "Bearer ${ConstData.TOKEN}")
        }
    }

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
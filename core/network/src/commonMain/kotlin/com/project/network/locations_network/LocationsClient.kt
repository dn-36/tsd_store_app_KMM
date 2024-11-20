package com.project.network.locations_network


import com.project.network.common.ConstData
import com.project.network.common.httpClientEngine
import com.project.network.locations_network.model.CreateLocationRequest
import com.project.network.locations_network.model.ResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
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

    // Создание новой локации
    suspend fun createLocation (

        name: String?, email: String?, phone: String?,

        default: Int?, text: String?,

        telegram: String?, whatsapp: String?, wechat: String?,

        point: List<Double>?, adres: String, contragent_id: Int,

        entity_id: Int, workers: List<Int>, langs: List<Int>

    ): HttpResponse {

        val requestBody = CreateLocationRequest(

            name = name,
            point = listOf(55.76070387306711,37.56401090435791).toString(),
            adres = adres,
            contragent_id = "${contragent_id}",
            email = email,
            phone = phone,
            default = default,
            text = text,
            telegram = telegram,
            whatsapp = whatsapp,
            wechat = wechat,
            entity_id = entity_id,
            workers = workers.toString(),
            langs = langs.toString()
        )
        return try {

            val response = client.post("https://delta.online/api/local") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Создание локации: ${response}")

            println("Создание локации: ${requestBody}")

            response

        } catch (e: Exception) {

            println("ошибка создания локации: Error - ${e.message}")

            throw e
        }
    }

    // обновление локации
    suspend fun updateLocation (

        id: Int, name: String?, email: String?, phone: String?,

        default: Int?, text: String?,

        telegram: String?, whatsapp: String?, wechat: String?,

        point: List<Double>?, adres: String, contragent_id: Int,

        entity_id: Int, workers: List<Int>, langs: List<Int>

    ): HttpResponse {

        val requestBody = CreateLocationRequest(

            name = name,
            point = listOf(55.76070387306711,37.56401090435791).toString(),
            adres = adres,
            contragent_id = "${contragent_id}",
            email = email,
            phone = phone,
            default = default,
            text = text,
            telegram = telegram,
            whatsapp = whatsapp,
            wechat = wechat,
            entity_id = entity_id,
            workers = workers.toString(),
            langs = langs.toString()
        )
        return try {

            val response = client.put("https://delta.online/api/local/${id}") {

                contentType(ContentType.Application.Json)

                setBody(requestBody)
            }
            println("Обновление локации: ${response}")

            println("Обновление локации: ${requestBody}")

            response

        } catch (e: Exception) {

            println("ошибка обновления локации: Error - ${e.message}")

            throw e
        }
    }

    // удаление локации
    suspend fun deleteLocation( id: Int ): HttpResponse {

        return try {

            val response = client.delete("https://delta.online/api/local/${id}") {

                contentType(ContentType.Application.Json)
            }

            println("Удаление локации: ${response.toString()}")

            response

        } catch (e: Exception) {

            println("ошибка удаления локации: Error - ${e.message}")

            throw e
        }
    }

    /*

name: новая тестовая спецификация
email: beldominika@yandex.ru
phone: 9899999
default: 0
text: ghjxee
telegram: telegtran
whatsapp: whatsap
wechat: wecgt
point:
adres: adres
contragent_id: 93
entity_id: 5
workers: [18]
langs: [1]

*/

}
package com.project.network.crm_network.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class ApiResponseCRMOutgoing(
    val id: Int?,
    val service_id: Int,
    val company_id: Int?,
    val project_id: Int?,
    val organization_id: Int?,
    val user_id: Int?,
    val active_company_id: Int?,
    val company_work: String?,
    val arenda_id: Int?,
    val status: String?,
    val text: String?,
    val ui: String?,
    val active: JsonElement? = null,
    val flesh: Int?,
    val del: Int?,
    val nomer: Int?,
    val statusid: Int?,
    val data: String?,
    val date_start: String?,
    val date_stop: String?,
    val price: Double?,
    val template: Int?,
    val status_pay: String?,
    val template_value: String?,
    val task: String?,
    val created_at: String,
    val updated_at: String,
    val entity_id: Int?,
    val group_entity_id: Int?,
    val check: Int?,
    val log_check: String?,
    val check_our: Int?,
    val log_check_our: Int?,
    val our_entity_id: Int?,
    val cafe_type: Int?,
    val resource_id: Int?,
    val cafe_id: Int?,
    val invoice_pay: String?,
    val type_pay: Int?,
    val specification_id: Int?,
    val account_entity_id: Int?,
    val verify_pay: String?,
    val system: Int?,
    val search_worker: Int?,
    val no_view_client: Int?,
    val is_order_creater: Int?,
    val to_local_id: Int?,
    val from_local_id: Int?,
    val cargos: List<Cargo>?,
    val projects: Project?,
    val service: Service?,
    val company: Company?,
    val companactiv: String?,
    val entity: Entity?,
    val groupentits: GroupEntity?,
    val entity_our: EntityOur?,
    val specs: Specs?,
    val `val`: List<Val>
)

@Serializable
data class Value(
    val id: Int?,
    val arenda_id: Int?,
    val type_id: Int?,
    @SerialName("val")val value: String?,
    val created_at: String?,
    val updated_at: String?,
    val items_type: ItemsType?
)

@Serializable
data class ItemsType(
    val id: Int?,
    val service_id: Int?,
    val name: String?,
    val type: String?,
    val metka: String?,
    val search: Int?,
    val req: Int?,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class Service(
    val id: Int?,
    val name: String?,
    val text: String?,
    val doc: String?,
    val ui: String?,
    val system: Int?,
    val width: Int?,
    val height: Int?,
    val service_type_doc: Int?,
    val comp_project: String?,
    val view: Int?,
    val view_comp_project: String?,
    val store: Int?,
    val delete_status: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val need_check: Int?,
    val check_entity_id: Int?,
    val check_group_entity_id: Int?,
    val need_check_our: Int?,
    val limit_group_entity_id: Int?,
    val default_entity_id: Int?,
    val background: String?,
    val image: String?,
    val items_value: List<ItemValue>?,
    val all_check_users_id: List<Int>?,
    val all_check_users_other: List<String>?
)

@Serializable
data class ItemValue(
    val id: Int?,
    val service_id: Int?,
    val name: String?,
    val type: String?,
    val metka: String?,
    val search: Int?,
    val req: Int?,
    val created_at: String?,
    val updated_at: String?
)

@Serializable
data class Company(
    val id: Int?,
    val name: String?,
    val creater_id: Int?,
    val company_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val own: Int?
)

@Serializable
data class EntityOur(
    val id: Int?,
    val own: Int?,
    val creater_id: Int?,
    val contragent_id: Int?,
    val company_id: Int?,
    val name: String?,
    val inn: String?,
    val kpp: String?,
    val okpo: String?,
    val ogrn: String?,
    val base: String?,
    val ur_address: String?,
    val fact_address: String?,
    val fio: String?,
    val phone: String?,
    val email: String?,
    val nds: String?,
    val print: String?,
    val sign: String?,
    val book_sign: String?,
    val ui: String?,
    val file_verify: String?,
    val verify: Int?,
    val created_at: String?,
    val updated_at: String?,
    val user_id: Int?,
    val company: Company?
)

@Serializable
data class Specs(
    val id: Int?,
    val company_id: Int?,
    val text: String?
)

@Serializable
data class Entity(
    val id: Int?,
    val own: Int?,
    val creater_id: Int?,
    val name: String?
    // другие возможные поля...
)

data class Activee(
    val id: Int,
    val service_id: Int
)

@Serializable
data class Project(
    val id: Int,
    val name: String
)

@Serializable
data class GroupEntity(
    val id: Int,
    val name: String
)


@Serializable
data class Cargo(
    val id: Int,
    val company_id: Int,
    val name: String,
    val status: String?, // Может быть null
    val number: String,
    val ui: String,
    val created_at: String,
    val updated_at: String,
    val from: String?, // Может быть null
    val text: String?, // Может быть null
    val to: String?, // Может быть null
    val from_point: String?, // Может быть null
    val to_point: String?, // Может быть null
    val push_entity_id: Int?, // Может быть null
    val pull_entity_id: Int?, // Может быть null
    val to_local_id: Int?, // Может быть null
    val from_local_id: Int?, // Может быть null
    val cargo_type_id: Int, // Обязательное поле
    val archive: Int,
    val delivery_id: Int?, // Может быть null
    val laravel_through_key: Int
)

@Serializable
data class Val(
    val id: Int,
    val arenda_id: Int,
    val type_id: Int,
    @Serializable(with = ValDetailSerializer::class)
    val `val`: ValDetailOrString? = null,
    val created_at: String,
    val updated_at: String,
    val items_type: ItemValue
)

@Serializable
data class ValDetailOrString(
    val detail: ValDetail? = null,
    val raw: String? = null
)

@Serializable
data class ValDetail(
    val id: Int,
    val name: String? = null,
    val text: String? = null,
    val email: String? = null,
    val email_verified_at: String? = null,
    val phone: String? = null,
    val ui: String? = null,
    val policy: Int? = null, // Сделано необязательным
    val created_at: String? = null,
    val updated_at: String? = null,
    val tema: String? = null,
    val active: Int? = null, // Сделано необязательным
    val inn: String? = null,
    val image: String? = null,
    val contragents: Int? = null, // Сделано необязательным
    val price: Double? = null,
    val lang_id: Int? = null, // Сделано необязательным
    val company: List<Company>? = null
)
object ValDetailSerializer : KSerializer<ValDetailOrString> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ValDetailOrString")

    override fun serialize(encoder: Encoder, value: ValDetailOrString) {
        when {
            value.detail != null -> encoder.encodeSerializableValue(ValDetail.serializer(), value.detail)
            value.raw != null -> encoder.encodeString(value.raw)
        }
    }

    override fun deserialize(decoder: Decoder): ValDetailOrString {
        val input = decoder as? JsonDecoder ?: throw SerializationException("Expected JsonDecoder")
        val element = input.decodeJsonElement()

        return if (element is JsonObject) {
            ValDetailOrString(detail = input.json.decodeFromJsonElement(ValDetail.serializer(), element))
        } else if (element is JsonPrimitive && element.isString) {
            ValDetailOrString(raw = element.content)
        } else {
            throw SerializationException("Unexpected JSON for ValDetailOrString: $element")
        }
    }
}
package com.project.network.cargo_network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CargoResponse(
    val id: Int,
    val company_id: Int,
    val name: String?,
    val status: String?,
    val number: String,
    val ui: String,
    val text: String?,
    val from: String?,
    val to: String?,
    val from_point: String?,
    val to_point: String?,
    val cargo_type_id: Int,
    val archive: Int,
    val to_workers: List<Worker>,
    val from_workers: List<Worker>,
    val push: CompanyDetails?,
    val pull: CompanyDetails?,
    val to_local: String?,
    val from_local: String?,
    val to_local_id: Int?,
    val from_local_id: Int?,
    val cargo_type: CargoType,
    val field_values: List<FieldValue>,
    val created_at: String,
    val updated_at: String,
    val crms: JsonElement?,
    val delivery: String?,
    val delivery_id: Int?
)

@Serializable
data class Worker(
    val id: Int?,
    val name: String?
    // Добавьте другие поля, если они есть
)

@Serializable
data class CompanyDetails(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val company: Company?,
    val own: Int?,
    val email: String?,
    val fact_address: String?,
    val fio: String?,
    val kpp: String?,
    val inn: String?,
    val nds: String?,
    val ogrn: String?,
    val okpo: String?,
    val phone: String?,
    val ur_address: String?,
    val accounts: JsonElement?
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
data class CargoType(
    val id: Int?,
    val name: String?
)

@Serializable
data class FieldValue(
    val id: Int?,
    val value: String?,
    val field: Field?
)

@Serializable
data class Field(
    val id: Int?,
    val name: String?,
    val type: String?,
    val unit: Unit?,
    val unit_id: Int?
)

@Serializable
data class Unit(
    val name: String?,
    val ui: String?,
    val id: Int?
)


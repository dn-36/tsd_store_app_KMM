package com.project.network.arrival_goods.model

import kotlinx.serialization.Serializable

@Serializable
data class EntityInfo(
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
    val user_id: Int?
)

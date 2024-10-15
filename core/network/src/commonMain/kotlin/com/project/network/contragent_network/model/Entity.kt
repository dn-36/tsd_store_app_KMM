package com.project.network.contragent_network.model

import com.project.network.contragent_network.model.Company
import kotlinx.serialization.Serializable

@Serializable
data class Entity(
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
    val nds: Int?,
    val ogrn: String?,
    val okpo: String?,
    val phone: String?,
    val ur_address: String?
)


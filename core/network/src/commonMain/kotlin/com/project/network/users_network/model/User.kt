package com.project.network.users_network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int?,
    val name: String?,
    val email: String? = null,
    @SerialName("email_verified_at") val emailVerifiedAt: String? = null,
    val phone: String?,
    val ui: String?,
    val policy: Int?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    val tema: String?,
    val active: Int?,
    val inn: String? = null,
    val image: String? = null,
    val contragents: Int?,
    val price: Int? = null,
    @SerialName("lang_id") val langId: Int?
)

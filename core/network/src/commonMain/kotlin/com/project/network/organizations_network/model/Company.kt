package com.project.network.organizations_network.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val creater_id: Int?,
    val created_at: String?,
    val updated_at: String?,
    val url: String? = null,
    val country: Int?,
    val title: String? = null,
    val description: String? = null,
    val logo: String? = null,
    val favicon: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val fon: String?,
    val slider_title: String? = null,
    val slider_view: Int?,
    val address: String? = null,
    val metric: String? = null,
    val fon_product: String?,
    val tg: String? = null,
    val wh: String? = null,
    val wc: String? = null,
    val valuta_id: Int? = null,
    val inn: String? = null,
    val type: String?,
    val user_pay: String? = null,
    val password_pay: String? = null,
    val point: String? = null,
    val point_image: String? = null,
    val tags: String? = null,
    val video: String? = null,
    val video_mobile: String? = null,
    val cart: String? = null,
    val head_text: String? = null,
    val head_image: String? = null,
    val head_title: String? = null,
    val head_button: String? = null,
    val head_href: String? = null,
    val lang_default_id: Int?,
    val valuta: Valuta? = null
)



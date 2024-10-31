package model

import kotlinx.serialization.SerialName

data class UserCRMModel(

    val id: Int?,
    val name: String?,
    val email: String? = null,
    val phone: String?,
    val ui: String?,
    val policy: Int?,
    val tema: String?,
    val active: Int?,
    val inn: String? = null,
    val image: String? = null,
    val contragents: Int?,
    val price: Int? = null,

)

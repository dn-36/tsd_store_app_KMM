package com.model

data class CurrencyResponseModel(
    val id: Int,
    val company_id: Int?,
    val name: String,
    val system_name: String,
    val curs: Double,
    val ui: String,
    val active: Int,
    val created_at: String?,
    val updated_at: String?,
    val view: CurrencyViewModel
)

data class CurrencyViewModel(
    val id: Int,
    val valuta_id: Int,
    val company_id: Int,
    val active: Int,
    val created_at: String,
    val updated_at: String
)


package com.project.network.arrival_goods.model
import kotlinx.serialization.Serializable

@Serializable
data class CreateRequest(
    val text: String, // Описание
    val contragent_expense_id: Int, // id контрагента расход
    val contragent_push_id: Int, // id контрагента приход
    val entity_expense_id: Int, // id юр. лица расход
    val entity_push_id: Int, // id юр. лица приход
    val store_id: Int, // id склада
    val is_push: Int, // 1 - приход, 0 - списание
    val products: List<ProductArrival> // Массив продуктов
)

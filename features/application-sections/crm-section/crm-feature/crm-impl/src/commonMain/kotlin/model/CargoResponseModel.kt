package model

import com.project.network.cargo_network.model.CargoType

data class CargoResponseModel(

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
    val to_local: String?,
    val from_local: String?,
    val to_local_id: Int?,
    val from_local_id: Int?,
    val cargo_type: CargoType,
    val created_at: String,
    val updated_at: String,
    val delivery: String?,
    val delivery_id: Int?

)

package model

import com.project.network.locations_network.LocationsClient
import com.project.network.locations_network.model.Contragent
import com.project.network.locations_network.model.Entity
import com.project.network.locations_network.model.Worker
import kotlinx.serialization.Serializable

data class LocationResponseModel(

    val id: Int?,
    val adres: String?,
    val name: String?,
    val default: Int?,
    val ui: String?,
    val email: String?,
    val phone: String?,
    val text: String?,
    val contragent:ContragentLocationModel?

    )

data class ContragentLocationModel(
    val id: Int?,
    val name: String?,
    val ui: String?,
    val own: Int?
)

package component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import model.ApiResponseCRMModel
import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

data class DataEntryState (

    val expendedService: Boolean = false,

    val expendedStatus: Boolean = false,

    val expendedEmployee: Boolean = false,

    val expendedLegalEntityPerformer: Boolean = false,

    val expendedSpecifications: Boolean = false,

    val expendedLocations: Boolean = false,

    val expendedGoodsAndServices: Boolean = false,


    val service: String = "",

    val legalEntityPerformer: String = "",

    val employee: String = "",

    val location: String = "",

    val specification: String = "",

    val statusText: String = "",

    val status: String = "",

    val task: String = "",

    val phoneNumber: String = "",

    val comment: String = "",

    val fio: String = "",

    val goodsAndServices: String = "",


    val selectedService: ServiceResponseModel? = null,

    val selectedSpecific: SpecificResponseModel? = null,

    val selectedLegalEntityPerformer: EntityContragentModel? = null,

    val selectedEmployee: UserCRMModel? = null,

    val selectedLocation: LocationResponseModel? = null,

    val selectedGoodsAndServices: SpecificResponseModel? = null,

    val selectedStatus: String = "",


    val filteredListSpecifications: List<SpecificResponseModel> = emptyList(),

    val filteredListGoodsAndServices: List<SpecificResponseModel> = emptyList(),

    val filteredListServices: List<ServiceResponseModel> = emptyList(),

    val filteredListEmployee: List<UserCRMModel> = emptyList(),

    val filteredListLegalEntities: List<EntityContragentModel> = emptyList(),

    val filteredListLocations: List<LocationResponseModel> = emptyList(),

    val filteredListStatus: List<String> = emptyList(),

    val isSet: Boolean = true

    )

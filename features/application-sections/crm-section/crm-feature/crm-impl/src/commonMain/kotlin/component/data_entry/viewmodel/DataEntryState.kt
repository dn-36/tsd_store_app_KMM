package component.data_entry.viewmodel

import androidx.compose.ui.graphics.Color
import model.CargoResponseModel
import model.ContragentResponseModel
import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

data class DataEntryState (

    val expendedService: Boolean = false,

    val expendedPaidFor: Boolean = false,

    val expendedVerified: Boolean = false,

    val expendedStatus: Boolean = false,

    val expendedEmployeePerformer: Boolean = false,

    val expendedLegalEntityPerformer: Boolean = false,

    val expendedLocationsPerformer: Boolean = false,

    val expendedEmployee: Boolean = false,

    val expendedLegalEntity: Boolean = false,

    val expendedLocations: Boolean = false,

    val expendedSpecifications: Boolean = false,

    val expendedGoodsAndServices: Boolean = false,

    val expendedCargo: Boolean = false,


    val service: String = "",

    val cargo: String = "",

    val legalEntityPerformer: String = "",

    val employeePerformer: String = "",

    val locationPerformer: String = "",

    val legalEntity: String = "",

    val employee: String = "",

    val location: String = "",

    val specification: String = "",

    val statusText: String = "",

    val status: String = "",

    val task: String = "",



    val selectedService: ServiceResponseModel? = null,

    val selectedCargo: CargoResponseModel? = null,

    val selectedPaidFor: Pair<String,Int>? = null,

    val selectedVerified: Pair<String,Int>? = null,

    val selectedSpecific: SpecificResponseModel? = null,

    val selectedLegalEntityPerformer: EntityContragentModel? = null,

    val selectedContragentPerformer: ContragentResponseModel? = null,

    val selectedEmployeePerformer: UserCRMModel? = null,

    val selectedLocationPerformer: LocationResponseModel? = null,


    val selectedLegalEntity: EntityContragentModel? = null,

    val selectedContragent: ContragentResponseModel? = null,

    val selectedEmployee: UserCRMModel? = null,

    val selectedLocation: LocationResponseModel? = null,



    val selectedGoodsAndServices: SpecificResponseModel? = null,

    val selectedStatus: Pair<String,Int> = Pair("Активна",1),


    val filteredListSpecifications: List<SpecificResponseModel> = emptyList(),

    val filteredListCargo: List<CargoResponseModel> = emptyList(),

    val filteredListGoodsAndServices: List<SpecificResponseModel> = emptyList(),

    val filteredListServices: List<ServiceResponseModel> = emptyList(),

    val filteredListEmployee: List<UserCRMModel> = emptyList(),

    val filteredListContragents: List<ContragentResponseModel> = emptyList(),

    val filteredListLocations: List<LocationResponseModel> = emptyList(),

    val filteredListStatus: List<String> = emptyList(),

    val isSet: Boolean = true,


    val totalPrice: Double? = null,


    val borderServiceColor: Color = Color.Gray,

    val containerServiceColor: Color = Color.White,


    val textFieldsValues: List<String> =  emptyList()

    )

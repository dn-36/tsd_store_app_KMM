package viewmodel

import model.ApiResponseCRMModel
import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

data class CRMState(

    val listIncomingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listOutgoingCRM: MutableList<ApiResponseCRMModel> = mutableListOf(),

    val listSpecifications: List<SpecificResponseModel> = emptyList(),

    val listServices: List<ServiceResponseModel> = emptyList(),

    val listEmployee: List<UserCRMModel> = emptyList(),

    val listLegalEntities: List<EntityContragentModel> = emptyList(),

    val listLocations: List<LocationResponseModel> = emptyList(),

    val listProjects: String = String(),


    val isIncoming: Boolean = true,

    val isOutgoing: Boolean = false,

    val isSet: Boolean = true,

    val isVisibilityDataEntryComponent: Float = 0f,

    val expendedService: Boolean = false,

    val expendedStatus: Boolean = false,

    val expendedEmployee: Boolean = false,

    val expendedLegalEntityPerformer: Boolean = false,

    val expendedSpecifications: Boolean = false,

    val expendedLocations: Boolean = false,

    val expendedGoodsAndServices: Boolean = false,

    )

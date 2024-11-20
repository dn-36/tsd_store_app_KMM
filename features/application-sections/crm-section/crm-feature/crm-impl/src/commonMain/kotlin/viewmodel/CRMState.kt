package viewmodel

import model.ApiResponseCRMModel
import model.CargoResponseModel
import model.ContragentResponseModel
import model.GroupEntityResponseModel
import model.LocationResponseModel
import model.ProductModel
import model.ProjectResponseModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel

data class CRMState(

    val listIncomingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listFilteredIncomingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listOutgoingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listFilteredOutgoingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listProducts: List<ProductModel> = emptyList(),

    val listSpecifications: List<SpecificResponseModel> = emptyList(),

    val listServices: List<ServiceResponseModel> = emptyList(),

    val listEmployee: List<UserCRMModel> = emptyList(),

    val listContragents: List<ContragentResponseModel> = emptyList(),

    val listCargo: List<CargoResponseModel> = emptyList(),

    val listGroupEntity: List<GroupEntityResponseModel> = emptyList(),

    val listLocations: List<LocationResponseModel> = emptyList(),

    val listProjects: List<ProjectResponseModel> = emptyList(),

    val listAlphaTools: List<Float> = emptyList(),


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


    val updateItem: ApiResponseCRMModel? = null,


    )

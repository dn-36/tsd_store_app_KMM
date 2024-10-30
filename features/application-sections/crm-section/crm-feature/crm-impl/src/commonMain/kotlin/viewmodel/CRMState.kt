package viewmodel

import model.ApiResponseCRMModel
import model.EntityModel

data class CRMState(

    val listIncomingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listOutgoingCRM: List<ApiResponseCRMModel> = emptyList(),

    val isIncoming: Boolean = true,

    val isOutgoing: Boolean = false,

    val isSet: Boolean = true,

    val entity: EntityModel? = null

)

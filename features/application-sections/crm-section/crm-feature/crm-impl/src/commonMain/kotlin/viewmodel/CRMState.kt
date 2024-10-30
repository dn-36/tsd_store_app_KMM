package viewmodel

import model.ApiResponseCRMModel

data class CRMState(

    val listIncomingCRM: List<ApiResponseCRMModel> = emptyList(),

    val listOutgoingCRM: String = ""

)

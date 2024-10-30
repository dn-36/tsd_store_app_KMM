package screen.viewmodel

import screen.model.ContragentResponseModel

data class ContragentsState(

    val listAllContragents: MutableList<ContragentResponseModel> = mutableListOf(),

    val isSet: Boolean = true,

    val isVisibleCreateAndUpdateComponent: Float = 0f,

    val isVisibleDeleteComponent: Float = 0f,

    val updatedItem: ContragentResponseModel? = null,

    val index: Int = 0

)

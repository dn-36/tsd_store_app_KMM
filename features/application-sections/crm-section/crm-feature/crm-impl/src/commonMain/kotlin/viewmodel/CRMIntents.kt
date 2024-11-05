package viewmodel

import kotlinx.coroutines.CoroutineScope
import model.ServiceItemCreateCRMModel

sealed class CRMIntents {

    data class SetScreen (val coroutineScope: CoroutineScope ): CRMIntents()

    data class SelectTypeCRM ( val index: Int ): CRMIntents()

    object Back: CRMIntents()
    data class CreateCRM ( val coroutineScope: CoroutineScope, val  serviceId: Int?,

                           val statusPay: Int?, val verifyPay: Int?, val task: String?,

                           val status: String?, val price: String?, val arendaId: Int?,

                           val specificationId: Int?, val projectId: Int?, val entityId: Int?,

                           val ourEntityId: Int?, val text: String?, val statusId: Int?,

                           val items: List<ServiceItemCreateCRMModel>? ): CRMIntents()

    object BackToCRMComponent: CRMIntents()

    data class OpenDataEntryComponent ( val coroutineScope: CoroutineScope ): CRMIntents()

}
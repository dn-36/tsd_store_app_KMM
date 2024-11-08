package viewmodel

import kotlinx.coroutines.CoroutineScope
import model.ApiResponseCRMModel
import model.ServiceItemCreateCRMModel

sealed class CRMIntents {

    data class SetScreen (val coroutineScope: CoroutineScope ): CRMIntents()

    data class SelectTypeCRM ( val index: Int ): CRMIntents()
    data class OpenUpdateDataEntryComponent ( val item: ApiResponseCRMModel,

                                              val coroutineScope: CoroutineScope ): CRMIntents()

    object Back: CRMIntents()
    data class CreateCRM (val coroutineScope: CoroutineScope, val  serviceId: Int?,

                          val statusPay: Int?, val verifyPay: Int?, val task: String?,

                          val to_local_id:Int?, val group_entity_id:Int?, val from_local_id:Int?,

                          val status: String?, val price: String?, val arendaId: Int?,

                          val specificationId: Int?, val projectId: Int?, val entityId: Int?,

                          val ourEntityId: Int?, val text: String?, val statusId: Int?,

                          val items: List<ServiceItemCreateCRMModel>? ): CRMIntents()

    data class UpdateCRM (val coroutineScope: CoroutineScope, val ui:String, val  serviceId: Int?,

                          val statusPay: Int?, val verifyPay: Int?, val task: String?,

                          val to_local_id:Int?, val group_entity_id:Int?, val from_local_id:Int?,

                          val status: String?, val price: String?, val arendaId: Int?,

                          val specificationId: Int?, val projectId: Int?, val entityId: Int?,

                          val ourEntityId: Int?, val text: String?, val statusId: Int?,

                          val items: List<ServiceItemCreateCRMModel>? ): CRMIntents()

    object BackToCRMComponent: CRMIntents()

    data class OpenCreateDataEntryComponent (val coroutineScope: CoroutineScope ): CRMIntents()

}
package viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class GoodsAndServicesIntents {

   object Back: GoodsAndServicesIntents()

   data class GetGoodsAndServices( val coroutineScope: CoroutineScope ): GoodsAndServicesIntents()

   data class OpenCreateDataEntry ( val coroutineScope: CoroutineScope ): GoodsAndServicesIntents()

   object BackFromDataEntry: GoodsAndServicesIntents()

}
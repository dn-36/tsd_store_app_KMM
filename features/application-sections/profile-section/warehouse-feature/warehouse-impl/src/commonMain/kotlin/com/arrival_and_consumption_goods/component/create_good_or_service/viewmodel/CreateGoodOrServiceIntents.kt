package com.arrival_and_consumption_goods.component.create_good_or_service.viewmodel

import com.arrival_and_consumption_goods.model.CategoryModel

sealed class CreateGoodOrServiceIntents {

    data class SetScreen( val lisCategory: List<CategoryModel>, val sku: String

    ): CreateGoodOrServiceIntents()


    data class InputTextName( val text: String ): CreateGoodOrServiceIntents()

    data class InputTextSku( val text: String ): CreateGoodOrServiceIntents()

    data class InputTextPrice( val text: String ): CreateGoodOrServiceIntents()

    data class InputTextDescriptionImage( val text: String ): CreateGoodOrServiceIntents()

    data class InputTextCategory( val text: String,

                                  val list: List<CategoryModel>

    ): CreateGoodOrServiceIntents()



    data class SelectCategory( val item: CategoryModel

    ): CreateGoodOrServiceIntents()

    data class SelectGoodOrService( val index: Int ): CreateGoodOrServiceIntents()

    data class SelectForSale( val index: Int ): CreateGoodOrServiceIntents()

    data class SelectUnderOrder( val index: Int ): CreateGoodOrServiceIntents()

    data class SelectIsStock( val index: Int ): CreateGoodOrServiceIntents()

    data class SelectDisplayStock( val index: Int ): CreateGoodOrServiceIntents()

    data class SelectDisplayOnSite( val index: Int ): CreateGoodOrServiceIntents()



    object DeleteSelectedCategory: CreateGoodOrServiceIntents()

    object DeleteSelectedGoodOrService: CreateGoodOrServiceIntents()

    object DeleteSelectedPhoto: CreateGoodOrServiceIntents()

    object DeleteSelectedForSale: CreateGoodOrServiceIntents()

    object DeleteSelectedUnderOrder: CreateGoodOrServiceIntents()

    object DeleteSelectedIsStock: CreateGoodOrServiceIntents()

    object DeleteSelectedDisplayStock: CreateGoodOrServiceIntents()

    object DeleteSelectedDisplayOnSite: CreateGoodOrServiceIntents()



    object MenuCategory: CreateGoodOrServiceIntents()

    object MenuForSale: CreateGoodOrServiceIntents()

    object MenuDisplayOnSite: CreateGoodOrServiceIntents()

    object MenuUnderOrder: CreateGoodOrServiceIntents()

    object MenuIsStock: CreateGoodOrServiceIntents()

    object MenuDisplayStock: CreateGoodOrServiceIntents()

    object MenuGoodOrService: CreateGoodOrServiceIntents()



    object CheckTF: CreateGoodOrServiceIntents()

}
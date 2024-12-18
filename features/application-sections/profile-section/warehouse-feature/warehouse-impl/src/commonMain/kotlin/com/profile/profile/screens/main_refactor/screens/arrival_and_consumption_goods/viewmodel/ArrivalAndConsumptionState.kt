package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

data class ArrivalAndConsumptionState (

    val listAllWarehouse: List<WarehouseArrivalAndConsumption> = emptyList(),

    val listAllContragent: List<ContragentResponseArrivalAndConsumption> = emptyList(),

    val listAlphaTools: List<Float> = emptyList(),

    val idLegalEntityParish: Int? = null,

    val idLegalEntityExpense: Int? = null,

    val idContragentExpense: Int? = null,

    val idContragentParish: Int? = null,

    val idWarehouse: Int? = null,

    val description: String = "",

    val isPush: Int = 0,

    val isVisibilityListProducts: Float = 0f,

    val isVisibilityCountProducts: Float = 0f,

    val isVisibilityDataEntryComponent: Float = 0f,

    val isVisibilityScannerComponent: Float = 0f,

    val isVisibilityAddProductsComponent: Float = 0f,

    val isVisibilityDeleteComponent: Float = 0f,

    val listProducts:List<AllProductArrivalAndConsumption> = emptyList(),

    val listArrivalOrConsumption: List<StoreResponseArrivalAndConsumption> = emptyList(),

    val listFilteredArrivalOrConsumption: List<StoreResponseArrivalAndConsumption> = emptyList(),

    val selectedProduct: ProductArrivalAndConsumption? = null,

    val updatedItem: StoreResponseArrivalAndConsumption? = null,

    val listSelectedProducts:List<ProductArrivalAndConsumption> = emptyList(),

    val updatedContragentExpense : ContragentResponseArrivalAndConsumption? = null,

    val updatedContragentParish : ContragentResponseArrivalAndConsumption? = null,

    val updatedEntityExpense : ContragentResponseArrivalAndConsumption? = null,

    val updatedEntityParish: ContragentResponseArrivalAndConsumption? = null,

    val updatedWarehouse : WarehouseArrivalAndConsumption? = null,

    val isUpdate: Boolean = false,

    val isSet: Boolean = true,

    val colorBorderCountTF: Color = Color.LightGray

    )

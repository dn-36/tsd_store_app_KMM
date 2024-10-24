package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.CreateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.DeleteArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.UpdateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArrivalAndConsumptionViewModel (

    val getContagentsUseCase: GetContagentsUseCase,

    val getProductsUseCase: GetProductsUseCase,

    val getWarehouseArrivalAndConsumptionUseCase: GetWarehouseArrivalAndConsumptionUseCase,

    val getArrivalAndConsumptionUseCase: GetArrivalAndConsumptionUseCase,

    val deleteArrivalOrConsumptionUseCase: DeleteArrivalOrConsumptionUseCase,

    val createArrivalOrConsumptionUseCase: CreateArrivalOrConsumptionUseCase,

    val updateArrivalOrConsumptionUseCase: UpdateArrivalOrConsumptionUseCase

) : ViewModel() {

    var arrivalAndConsumptionState by mutableStateOf(ArrivalAndConsumptionState())

    fun processIntent (intent: ArrivalAndConsumptionIntents) {

        when (intent) {

            is ArrivalAndConsumptionIntents.ArrivalOrConsumption -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getContagentsUseCase.execute ( onGet = { newListContragents ->

                        val newList = mutableListOf<ContragentResponseArrivalAndConsumption>()

                        newListContragents.forEach {

                            val newListEntity = mutableListOf<EntityArrivalAndConsumption>()

                                if(it.entits != null) {

                                    it.entits!!.forEach { entity ->

                                        newListEntity.add(EntityArrivalAndConsumption(

                                            id = entity.id,

                                            name = entity.name,

                                            ui = entity.ui

                                        ))

                                    }
                                }


                        newList.add(

                            ContragentResponseArrivalAndConsumption(

                            id = it.id,

                            name = it.name,

                            ui = it.ui,

                            own = it.own,

                            entits = newListEntity

                        )
                        )

                        }

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listAllContragent = newList,

                        )

                    })

                    getWarehouseArrivalAndConsumptionUseCase.execute ( onGet = { listAllWarehouse ->

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listAllWarehouse = listAllWarehouse,

                            isUpdate = false,

                            isVisibilityDataEntryComponent = mutableStateOf(1f),

                            isPush = intent.isPush

                            )

                    })

                }

            }

            is ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteArrivalOrConsumptionUseCase.execute( ui = intent.ui )

                    processIntent(ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods(intent.coroutineScope))

                }

            }

            is ArrivalAndConsumptionIntents.BackAddProducts ->  backAddProducts()

            is ArrivalAndConsumptionIntents.Update -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    updateArrivalOrConsumptionUseCase.execute (

                        productUi = arrivalAndConsumptionState.updatedItem!!.ui!!,

                        idLegalEntityParish = arrivalAndConsumptionState.idLegalEntityParish,

                        idLegalEntityExpense = arrivalAndConsumptionState.idLegalEntityExpense,

                        idContragentExpense = arrivalAndConsumptionState.idContragentExpense,

                        idContragentParish = arrivalAndConsumptionState.idContragentParish,

                        idWarehouse = arrivalAndConsumptionState.idWarehouse,

                        isPush = arrivalAndConsumptionState.isPush,

                        listProducts = arrivalAndConsumptionState.listSelectedProducts)

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        isVisibilityAddProductsComponent = mutableStateOf(0f)

                    )

                }

            }

            is ArrivalAndConsumptionIntents.UpdateButton -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                getContagentsUseCase.execute ( onGet = { newListContragents ->

                    val newList = mutableListOf<ContragentResponseArrivalAndConsumption>()

                    newListContragents.forEach {

                        val newListEntity = mutableListOf<EntityArrivalAndConsumption>()

                        if(it.entits != null) {

                            it.entits.forEach { entity ->

                                newListEntity.add(EntityArrivalAndConsumption(

                                    id = entity.id,

                                    name = entity.name,

                                    ui = entity.ui

                                ))

                            }
                        }


                        newList.add(

                            ContragentResponseArrivalAndConsumption(

                                id = it.id,

                                name = it.name,

                                ui = it.ui,

                                own = it.own,

                                entits = newListEntity

                            )
                        )

                    }

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        listAllContragent = newList,

                        )

                })

                getWarehouseArrivalAndConsumptionUseCase.execute ( onGet = { listAllWarehouse ->

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        listAllWarehouse = listAllWarehouse,

                        isPush = intent.item.is_push!!

                    )

                })



                    getProductsUseCase.execute ( onGet = { listAllProducts ->

                        val newListContragents = mutableListOf<ContragentResponseArrivalAndConsumption>()

                        arrivalAndConsumptionState.listAllContragent.forEach { item ->

                            if(item.entits != null ){

                                if(item.entits.isNotEmpty()){

                                    newListContragents.add(

                                        ContragentResponseArrivalAndConsumption(

                                        id = item.id,

                                        name = item.name,

                                        ui = item.ui,

                                        own = item.own,

                                        entits = item.entits

                                    )
                                    )

                                }

                            }

                        }

                        val newListWarehouse = mutableListOf<WarehouseArrivalAndConsumption>()

                        arrivalAndConsumptionState.listAllWarehouse.forEach { item ->

                            if(item.stores != null ){

                                if(item.stores.isNotEmpty()){

                                    newListWarehouse.add(item)

                                }

                            }

                        }


                        val newSelectedProduct = mutableListOf<ProductArrivalAndConsumption>()

                        listAllProducts.forEach { item ->

                            intent.item.products!!.forEach {

                                if ( it!!.product_id == item.id ) {

                                    newSelectedProduct.add(ProductArrivalAndConsumption(product = item, count = it.count!!))

                                }

                            }
                        }

                        val newContragentExpense =  newListContragents.find { it.id == intent.item.contragent_id }

                        val newContragentParish =  newListContragents.find { it.id == intent.item.contragent_push_id }

                        val newContragentEntityExpense =  newListContragents.find { it.entits!![0].id == intent.item.entity_id }

                        val newContragentEntityParish =  newListContragents.find { it.entits!![0].id == intent.item.entity_push_id }

                        val newWarehouse =  newListWarehouse.find { it.stores[0]!!.id == intent.item.store_id}


                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            updatedContragentEntityExpense = newContragentEntityExpense,

                            updatedWarehouse = newWarehouse,

                            updatedContragentEntityParish = newContragentEntityParish,

                            updatedContragentExpense = newContragentExpense,

                            updatedContragentParish = newContragentParish,

                            listSelectedProducts = newSelectedProduct,

                            listProducts = listAllProducts,

                            updatedItem = intent.item,

                            isUpdate = true,

                            isVisibilityDataEntryComponent = mutableStateOf(1f)


                        )

                    })
            }

                println("14")
                println("14")
                println("14")
                println("ItemProducts ${intent.item.products}")

            }

            is ArrivalAndConsumptionIntents.CreateArrivalOrConsumption ->  {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    if ( arrivalAndConsumptionState.listSelectedProducts.size != 0) {

                        createArrivalOrConsumptionUseCase.execute(

                            idLegalEntityParish = arrivalAndConsumptionState.idLegalEntityParish,

                            idLegalEntityExpense = arrivalAndConsumptionState.idLegalEntityExpense,

                            idContragentExpense = arrivalAndConsumptionState.idContragentExpense,

                            idContragentParish = arrivalAndConsumptionState.idContragentParish,

                            idWarehouse = arrivalAndConsumptionState.idWarehouse,

                            isPush = arrivalAndConsumptionState.isPush,

                            listProducts = arrivalAndConsumptionState.listSelectedProducts
                        )

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            isVisibilityAddProductsComponent = mutableStateOf(0f)

                        )

                    }

                }

            }

            is ArrivalAndConsumptionIntents.BackListProducts ->  backListProducts()

            is ArrivalAndConsumptionIntents.Ready ->  ready( intent.count )

            is ArrivalAndConsumptionIntents.SelectProducts ->  selectProduct( intent.selectedProducts )

            is ArrivalAndConsumptionIntents.Scanner ->  scanner()

            is ArrivalAndConsumptionIntents.SelectFromList -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    getProductsUseCase.execute ( onGet = { listAllProducts ->

                        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                            listProducts = listAllProducts,

                            isVisibilityListProducts = mutableStateOf(1f),

                        )

                    })

                }

            }

            is ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods -> {

            intent.coroutineScope.launch (Dispatchers.IO) {

                getArrivalAndConsumptionUseCase.execute ( onGet = { listArrivalAndConsumption ->

                    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                        listAllArrivalOrConsumption = listArrivalAndConsumption

                    )

                } )

            }

            }

            is ArrivalAndConsumptionIntents.BackDataEntry -> {

                backDataEntry()
            }

            is ArrivalAndConsumptionIntents.Next -> {

                next( intent.idLegalEntityParish, intent.idLegalEntityExpense, intent.idContragentExpense,

                    intent.idContragentParish, intent.idWarehouse )

            }

        }
    }

    fun backAddProducts(){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityAddProductsComponent = mutableStateOf(0f),

            isVisibilityDataEntryComponent = mutableStateOf(1f),

        )

    }

    fun backListProducts(){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityListProducts = mutableStateOf(0f),

            isVisibilityAddProductsComponent = mutableStateOf(1f)

        )

    }

    fun selectProduct(selectedProduct: ProductArrivalAndConsumption){

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            isVisibilityCountProducts = mutableStateOf(1f),

            isVisibilityListProducts = mutableStateOf(0f),

            selectedProduct = selectedProduct

        )

    }

    fun scanner() {

        // Navigation.navigator.push()

    }


    fun ready ( count: Int ) {

        val newProduct = arrivalAndConsumptionState.selectedProduct

        newProduct!!.count = count

        val newList = arrivalAndConsumptionState.listSelectedProducts.toMutableList()

        newList.add(newProduct)

        arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

            selectedProduct = newProduct,

            isVisibilityCountProducts = mutableStateOf(0f),

            listSelectedProducts = newList

        )

    }

    fun backDataEntry(){

    arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

        isVisibilityDataEntryComponent = mutableStateOf(0f)

    )

    }

    fun next ( idLegalEntityParish: Int?, idLegalEntityExpense: Int?, idContragentExpense: Int? ,

    idContragentParish: Int?, idWarehouse: Int? ) {

        if ( idLegalEntityParish != null && idLegalEntityExpense != null &&

            idContragentExpense != null && idContragentParish != null && idWarehouse != null ) {

            arrivalAndConsumptionState = arrivalAndConsumptionState.copy(

                isVisibilityAddProductsComponent = mutableStateOf(1f),

                isVisibilityDataEntryComponent = mutableStateOf(0f),

                idContragentExpense = idContragentExpense,

                idContragentParish = idContragentParish,

                idLegalEntityExpense = idLegalEntityExpense,

                idLegalEntityParish = idLegalEntityParish,

                idWarehouse = idWarehouse

            )

        }

    }

}
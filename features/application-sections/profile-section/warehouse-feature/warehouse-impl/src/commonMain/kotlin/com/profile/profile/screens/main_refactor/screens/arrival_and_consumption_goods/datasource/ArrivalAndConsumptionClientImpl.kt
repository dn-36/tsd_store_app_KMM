package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.datasource

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentInfoArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductDataArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreInfoArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption
import com.project.network.arrival_goods.ArrivalGoodsClient
import com.project.network.arrival_goods.model.CreateRequest
import com.project.network.arrival_goods.model.Product
import com.project.network.contragent_network.ContragentClient
import com.project.network.warehouse_network.WarehouseClient
import product_network.ProductApiClient

class ArrivalAndConsumptionClientImpl (

    private val warehouseClient: WarehouseClient,

    private val contragentsClient: ContragentClient,

    private val productsClient: ProductApiClient,

    private val arrivalAndConsumptionClient: ArrivalGoodsClient

) : ArrivalAndConsumptionClientApi {

    override suspend fun getContragents( onGet: (newListContragents: List<ContragentResponseArrivalAndConsumption> ) -> Unit) {

        val newList = mutableListOf<ContragentResponseArrivalAndConsumption>()

        contragentsClient.getContragents().forEach {

            val newListEntity = mutableListOf<EntityArrivalAndConsumption>()

            if(it.entits != null) {

                it.entits!!.forEach { entity ->

                    newListEntity.add(
                        EntityArrivalAndConsumption(

                        id = entity.id,

                        name = entity.name,

                        ui = entity.ui

                    )
                    )

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

        onGet( newList )

    }

    override suspend fun deleteArrivalOrConsumption(ui:String) {

        arrivalAndConsumptionClient.deleteProduct(ui)

    }

    override suspend fun getWarehouseArrivalAndConsumption( onGet: (listAllWarehouse: List<WarehouseArrivalAndConsumption> ) -> Unit) {

        val newListWarehouse = mutableListOf<WarehouseArrivalAndConsumption>()

        warehouseClient.getWarehouse().forEach {

            if(it.stores.isNotEmpty()) {

                var newListStores = mutableListOf<StoreArrivalAndConsumption>()

                it.stores.forEach { store ->

                    println("Store : ${store}")

                    newListStores.add(

                        StoreArrivalAndConsumption(

                            id = store!!.id,

                            name = store!!.name,

                            ui = store!!.ui


                        )
                    )
                    println("NewListStores: ${newListStores}")
                }

                println("NewListStoresTwo: ${newListStores}")

                newListWarehouse.add(

                    WarehouseArrivalAndConsumption(

                        stores = newListStores,

                        name = it.name

                    )
                )

            }

        }

        println("WarehouseCHeck : ${newListWarehouse}")

        onGet( newListWarehouse )
    }

    override suspend fun getProducts( onGet: (listAllProducts: List<AllProductArrivalAndConsumption> ) -> Unit ) {

        val newList = mutableListOf<AllProductArrivalAndConsumption>()

        productsClient.getProductNames().forEach {

            newList.add(
                AllProductArrivalAndConsumption(

                id = it.id,

                name = it.name,

                ui = it.ui

            )
            )

        }

        onGet ( newList )

    }

    override suspend fun getArrivalAndConsumption( onGet: (listArrivalAndConsumption: List<StoreResponseArrivalAndConsumption> ) -> Unit ) {

        val newListArrivalAndConsumption = mutableListOf<StoreResponseArrivalAndConsumption>()

        arrivalAndConsumptionClient.getProducts().forEach {

            val newListProducts = mutableListOf<ProductDataArrivalAndConsumption>()

            if(it.products != null) {

                it.products!!.forEach { product ->

                newListProducts.add(ProductDataArrivalAndConsumption(

                  product_id = product!!.product_id,

                    count = product!!.count

                )

                )

                }

            }

            newListArrivalAndConsumption.add ( StoreResponseArrivalAndConsumption(

             id = it.id!!,

                store_id = it.store_id!!,

                contragent_id = it.contragent_id!!,

                ui = it.ui!!,

                is_push = it.is_push!!,

                contragent_push_id = it.contragent_push_id!!,

                entity_id = it.entity_id!!,

                entity_push_id = it.entity_push_id!!,

                products = newListProducts,

                store = StoreInfoArrivalAndConsumption(

                   id = it.store!!.id,

                    name = it.store!!.name

                ),

                created_at = it.created_at,

                contragent = ContragentInfoArrivalAndConsumption(

                    id = it.contragent!!.id ,

                    name = it.contragent!!.name

                )



            ))

        }

        onGet ( newListArrivalAndConsumption )

    }

    override suspend fun createArrivalOrConsumption( idLegalEntityParish: Int?,

                                                     idLegalEntityExpense: Int?,

                                                     idContragentExpense: Int?,

                                                     idContragentParish: Int?,

                                                     idWarehouse: Int?,

                                                     isPush: Int ,

                                                     listProducts:List<ProductArrivalAndConsumption> ) {

        println("create usecase "+arrivalAndConsumptionClient.createProduct(
            text = "",
            idContragentExpense?:0,
            idContragentParish?:0,
            idLegalEntityExpense?:0,
            idLegalEntityExpense?:0,
            idWarehouse?:0,
            isPush,
            listProducts.map {
              ArrivalGoodsClient.Product(
                  ArrivalGoodsClient.ProductInfo(
                  it.product.id?:0,
                  it.count
                  )
              )
            }

        )
        )
    }

    override suspend fun updateArrivalOrConsumption(

        productUi: String ,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int,

        listProducts: List<ProductArrivalAndConsumption>

    ) {

        println("update usecase "+arrivalAndConsumptionClient.updateProduct(

            productUi = productUi ,

            CreateRequest( text = "",
            idContragentExpense?:0,
            idContragentParish?:0,
            idLegalEntityExpense?:0,
            idLegalEntityExpense?:0,
            idWarehouse?:0,
            isPush,
            products = listProducts.map {
                ArrivalGoodsClient.Product(
                    ArrivalGoodsClient.ProductInfo(
                        it.product.id?:0,
                        it.count
                    )
                )
            }
            )
        )
        )

    }

}
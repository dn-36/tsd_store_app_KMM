package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.datasource

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.project.network.arrival_goods.ArrivalGoodsClient
import com.project.network.arrival_goods.model.ProductArrival
import com.project.network.arrival_goods.model.StoreResponse
import com.project.network.contragent_network.ContragentClient
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.WarehouseClient
import com.project.network.warehouse_network.model.Warehouse
import product_network.ProductApiClient
import product_network.model.Product

class ArrivalAndConsumptionClientImpl (

    private val warehouseClient: WarehouseClient,

    private val contragentsClient: ContragentClient,

    private val productsClient: ProductApiClient,

    private val arrivalAndConsumptionClient: ArrivalGoodsClient

) : ArrivalAndConsumptionClientApi {

    override suspend fun getContragents( onGet: (newListContragents: List<ContragentResponse> ) -> Unit) {

        onGet(contragentsClient.getContragents())

    }

    override suspend fun getWarehouseArrivalAndConsumption( onGet: (listAllWarehouse: List<Warehouse> ) -> Unit) {

        val newListWarehouse = mutableListOf<Warehouse>()

        warehouseClient.getWarehouse().forEach {

            if (it.stores.isNotEmpty()) {

                newListWarehouse.add(it)

            }

        }

        onGet(newListWarehouse)
    }

    override suspend fun getProducts( onGet: (listAllProducts: List<Product> ) -> Unit ) {

        onGet ( productsClient.getProductNames() )

    }

    override suspend fun getArrivalAndConsumption( onGet: (listArrivalAndConsumption: List<StoreResponse> ) -> Unit ) {

        onGet ( arrivalAndConsumptionClient.getProducts() )

    }

    override suspend fun createArrivalOrConsumption( idLegalEntityParish: Int?,

                                                     idLegalEntityExpense: Int?,

                                                     idContragentExpense: Int?,

                                                     idContragentParish: Int?,

                                                     idWarehouse: Int?,

                                                     isPush: Int ,

                                                     listProducts:List<ProductArrivalAndConsumption> ) {

       var newList = mutableListOf<ProductArrival>()

        listProducts.forEach { item ->

            newList.add(ProductArrival( id = item.product.id!!, count = item.count))

        }

        arrivalAndConsumptionClient.createProduct(text = "", contragent_expense_id = idContragentExpense!!,

            contragent_push_id = idContragentParish!!, entity_expense_id = idLegalEntityExpense!!,

            entity_push_id = idLegalEntityParish!!, store_id = idWarehouse!!,

            is_push = isPush, products = newList)



    }

}
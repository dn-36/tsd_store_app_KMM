package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.datasource

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.project.network.contragent_network.ContragentClient
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.WarehouseClient
import com.project.network.warehouse_network.model.Warehouse
import product_network.ProductApiClient
import product_network.model.Product

class ArrivalAndConsumptionClientImpl (

    private val warehouseClient: WarehouseClient,

    private val contragentsClient: ContragentClient,

    private val productsClient: ProductApiClient

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

    override suspend fun getProducts( onGet: (listAllProducts: List<Product> ) -> Unit) {

        onGet ( productsClient.getProductNames() )

    }

}
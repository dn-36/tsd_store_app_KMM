package com.arrival_and_consumption_goods.datasource

import com.arrival_and_consumption_goods.model.CategoryLangModel
import com.arrival_and_consumption_goods.model.CategoryModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ContragentInfoArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.arrival_and_consumption_goods.model.ProductDataArrivalAndConsumption
import com.arrival_and_consumption_goods.model.StoreArrivalAndConsumption
import com.arrival_and_consumption_goods.model.StoreInfoArrivalAndConsumption
import com.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.arrival_goods.ArrivalGoodsClient
import com.project.network.arrival_goods.model.CreateRequest
import com.project.network.category_network.CategoryClient
import com.project.network.contragent_network.ContragentClient
import com.project.network.warehouse_network.WarehouseClient
import product_network.ProductApiClient

class ArrivalAndConsumptionClientImpl (

    private val warehouseClient: WarehouseClient,

    private val contragentsClient: ContragentClient,

    private val productsClient: ProductApiClient,

    private val arrivalAndConsumptionClient: ArrivalGoodsClient,

    private val categoryClient: CategoryClient,

    private val sharedPrefsApi: SharedPrefsApi

) : ArrivalAndConsumptionClientApi {

    override suspend fun getContragents(): List<ContragentResponseArrivalAndConsumption> {

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

        return newList

    }

    override suspend fun deleteArrivalOrConsumption(ui:String) {

        arrivalAndConsumptionClient.deleteProduct(ui)

    }

    override suspend fun getWarehouseArrivalAndConsumption(): List<WarehouseArrivalAndConsumption> {

        val newListWarehouse = mutableListOf<WarehouseArrivalAndConsumption>()

        warehouseClient.getWarehouse().forEach {

            if( it.stores.isNotEmpty()) {

                val newListStores = mutableListOf<StoreArrivalAndConsumption>()

                it.stores.forEach { store ->

                    println("Store : ${store}")

                    newListStores.add(

                        StoreArrivalAndConsumption(

                            id = store!!.id,

                            name = store.name,

                            ui = store.ui


                        )
                    )

                }

                newListWarehouse.add(

                    WarehouseArrivalAndConsumption(

                        stores = newListStores,

                        name = it.name

                    )
                )

            }

        }

        println("WarehouseCHeck : ${newListWarehouse}")

       return newListWarehouse
    }

    override suspend fun getProducts(): List<AllProductArrivalAndConsumption> {

        val newList = mutableListOf<AllProductArrivalAndConsumption>()

        productsClient.getProductNames().forEach {

            newList.add(

                AllProductArrivalAndConsumption(

                id = it.id,

                sku = it.sku,

                name = it.name,

                ui = it.ui

            )

            )

        }

       return newList

    }

    override suspend fun getArrivalAndConsumption(): List<StoreResponseArrivalAndConsumption> {

        val newListArrivalAndConsumption = arrivalAndConsumptionClient.getProducts().map { item ->

            StoreResponseArrivalAndConsumption(

                id = item.id!!,

                text = item.text ?: "",

                store_id = item.store_id!!,

                contragent_id = item.contragent_id!!,

                ui = item.ui!!,

                is_push = item.is_push!!,

                contragent_push_id = item.contragent_push_id!!,

                entity_id = item.entity_id!!,

                entity_push_id = item.entity_push_id!!,

                products = item.products?.map { product ->

                    ProductDataArrivalAndConsumption(

                        product_id = product!!.product_id,

                        count = product.count!!
                    )
                } ?: emptyList(),

                store = StoreInfoArrivalAndConsumption(

                    id = item.store!!.id,

                    name = item.store!!.name
                ),
                created_at = item.created_at,

                contragent = ContragentInfoArrivalAndConsumption(

                    id = if (item.is_push == 0) item.contragent_id else item.contragent_push_id,

                    name = if (item.is_push == 0) item.contragent!!.name else item.contragent_push!!.name
                )
            )
        }

        return newListArrivalAndConsumption

    }

    override suspend fun createArrivalOrConsumption(

        description: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int,

        listProducts: List<ProductArrivalAndConsumption>
    ) {

        println("create usecase "+arrivalAndConsumptionClient.createProduct(
            text = description,
            idContragentExpense?:0,
            idContragentParish?:0,
            idLegalEntityExpense?:0,
            idLegalEntityParish?:0,
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

        description: String,

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

            CreateRequest( text = description,
            idContragentExpense?:0,
            idContragentParish?:0,
            idLegalEntityExpense?:0,
            idLegalEntityParish?:0,
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

    override suspend fun createGoodOrService(

        name: String,
        //video_youtube: String,
        //ediz_id: Int?,
        category_id: Int?,
        is_product: Int,
        //is_sale: Int,
        //system_category_id: Int?,
        //is_view_sale: Int,
        //is_order: Int,
        //is_store: Int,
        //is_store_view: Int,
        sku: String,
        text_image: String,
        price: Float?,
        //tags: List<String>,
        //variantes: List<String>,
        //divisions: String,
        image_upload: String?

    ) {

        productsClient.createGoodOrService(name, "", null, category_id, is_product,

            1, null, 1, 0, 1, 1, sku,

            text_image, price, listOf(), listOf(), "", image_upload )

    }

    override suspend fun getCategories(): List<CategoryModel> {

        categoryClient.init(sharedPrefsApi.getToken()?:"")

        return categoryClient.getCategories().map {

            CategoryModel(

                id = it.id,
                name  = it.name,
                creater_id = it.creater_id,
                company_id = it.company_id,
                created_at = it.created_at,
                updated_at = it.updated_at,
                url = it.url,
                ui = it.ui,
                category_langs = it.category_langs?.map {

                    CategoryLangModel(

                        id = it.id,
                        name = it.name,
                        lang_id = it.lang_id,
                        created_at = it.created_at,
                        updated_at = it.updated_at,
                        category_id = it.category_id

                    )

                }

            )

        }


    }

}
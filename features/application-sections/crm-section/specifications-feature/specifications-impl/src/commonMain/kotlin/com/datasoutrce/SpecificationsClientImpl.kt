package com.datasoutrce

import com.domain.repository.SpecificationsClientApi
import com.model.CategoryLangModel
import com.model.CategoryModel
import com.model.CategoryProductModel
import com.model.ContragentResponseModel
import com.model.CurrencyResponseModel
import com.model.CurrencyViewModel
import com.model.ElementSpecification
import com.model.EntityContragentModel
import com.model.LocalModel
import com.model.LocalStoreModel
import com.model.ProductModel
import com.model.ProductResponseModel
import com.model.SpecItemModel
import com.model.SpecificResponseModel
import com.model.StoreModel
import com.model.WarehouseModel
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.contragent_network.ContragentClient
import com.project.network.specifications_network.SpecificationsClient
import com.project.network.specifications_network.model.Items
import com.project.network.valuta_network.model.CurrencyClient
import com.project.network.warehouse_network.WarehouseClient
import product_network.ProductApiClient

class SpecificationsClientImpl(

    private val sharedPrefsApi: SharedPrefsApi,

    private val specificationsClient: SpecificationsClient,

    private val contragentsClient: ContragentClient,

    private val currencyClient: CurrencyClient,

    private val warehouseClient: WarehouseClient,

    private val productApiClient: ProductApiClient

): SpecificationsClientApi {

    override suspend fun getToken(): String {

        return sharedPrefsApi.getToken()?:""

    }

    override suspend fun getSpecifications(): List<SpecificResponseModel> {

        specificationsClient.init(getToken())

        return specificationsClient.getSpecifications().map {

            SpecificResponseModel(

                id = it.id,
                company_id = it.company_id,
                text = it.text,
                ui = it.ui,
                price = it.price,
                status = it.status,
                valuta_id = it.valuta_id,
                created_at = it.created_at,
                local_store_id = it.local_store_id,
                entity_id = it.entity_id,
                specification_id = it.specification_id,
                spec_item = it.spec_item?.map {

                    SpecItemModel(

                     id = it.id,
                        spec_id = it.spec_id,
                        product_id = it.product_id,
                        block = it.block,
                        count = it.count,
                        price =it.price,
                        nds = it.nds,
                        text = it.text,
                        price_id = it.price_id,
                        product =  if ( it.product != null ) ProductModel(

                            ui = it.product!!.ui,
                            id = it.product!!.id,
                            name = it.product!!.name,
                            text = it.product!!.text,
                            price = it.product!!.price,
                            category =  if ( it.product!!.category != null ) CategoryModel(

                                id = it.product!!.category!!.id,
                                name = it.product!!.category!!.name,
                                creater_id = it.product!!.category!!.creater_id,
                                company_id = it.product!!.category!!.company_id,
                                created_at = it.product!!.category!!.created_at,
                                updated_at = it.product!!.category!!.updated_at,
                                url = it.product!!.category!!.url,
                                ui = it.product!!.category!!.ui,
                                category_langs = it.product!!.category!!.category_langs?.map {

                                 CategoryLangModel(

                                     id = it.id,
                                     name = it.name,
                                     lang_id = it.lang_id,
                                     created_at = it.created_at,
                                     updated_at = it.updated_at,
                                     category_id = it.category_id

                                 )

                                }

                            ) else null,

                            local_store = it.product!!.local_store?.map {

                            LocalStoreModel(

                                id = it.id,
                                store_id = it.store_id,
                                product_id = it.product_id,
                                stock = it.stock,
                                reserve = it.reserve,
                                order = it.order,
                                created_at = it.created_at,
                                updated_at = it.updated_at,
                                local =  if (it.local != null) LocalModel(

                                 id = it.local!!.id,
                                    name = it.local!!.name,
                                    ui = it.local!!.ui,
                                    company_id = it.local!!.company_id,
                                    local_id = it.local!!.local_id,
                                    created_at = it.local!!.created_at,
                                    updated_at = it.local!!.updated_at,
                                    default = it.local!!.default

                                ) else null ,

                                serials = it.serials

                            )

                            }

                        ) else null

                    )

                }

            )

        }
    }

    override suspend fun getContragents(): List<ContragentResponseModel> {

        return contragentsClient.getContragents().map {

            ContragentResponseModel(

                id = it.id,
                name = it.name,
                ui = it.ui,
                own = it.own,
                entities = it.entits?.map {

                    EntityContragentModel(

                        id = it.id,
                        name = it.name,
                        ui = it.ui

                    )

                }

            )

        }

    }

    override suspend fun getCurrency(): List<CurrencyResponseModel> {

        currencyClient.init(sharedPrefsApi.getToken()?:"")

        return currencyClient.getCurrency().map {

            CurrencyResponseModel(

                id = it.id,
                company_id = it.company_id,
                name = it.name,
                system_name = it.system_name,
                curs = it.curs,
                ui = it.ui,
                active = it.active,
                created_at = it.created_at,
                updated_at = it.updated_at,
                view =  CurrencyViewModel(

                    id = it.view.id,
                    valuta_id = it.view.valuta_id,
                    company_id = it.view.company_id,
                    active = it.view.active,
                    created_at = it.view.created_at,
                    updated_at = it.view.updated_at

                )

            )

        }

    }

    override suspend fun getWarehouse(): List<WarehouseModel> {

        val newListWarehouse = mutableListOf<WarehouseModel>()

        warehouseClient.getWarehouse().forEach {

            if(it.stores.isNotEmpty()) {

                val newListStores = mutableListOf<StoreModel>()

                it.stores.forEach { store ->

                    println("Store : ${store}")

                    newListStores.add(

                        StoreModel(

                            id = store!!.id,

                            name = store.name,

                            ui = store.ui


                        )
                    )

                }

                newListWarehouse.add(

                    WarehouseModel(

                        stores = newListStores,

                        name = it.name

                    )
                )

            }

        }

    return  newListWarehouse/*warehouseClient.getWarehouse().map {

        WarehouseModel(

            stores =  it.stores.map { store ->

                StoreModel(
                    id = if ( store != null ) store.id else null,
                    name = if ( store != null ) store.name else null,
                    ui = if ( store != null ) store.ui else null
                )

            } ,

            name = it.name
        )

    }*/

    }

    override suspend fun getProducts (): List<ProductResponseModel> {

        return productApiClient.getProductNames().map {

            ProductResponseModel (

                id = it.id,
                sku = it.sku,
                name = it.name,
                ui = it.ui,
                price = it.price,
                category = if ( it.category != null ) CategoryProductModel(

                id = it.category!!.id,
                    name = it.category!!.name,
                    creater_id = it.category!!.creater_id,
                    company_id = it.category!!.company_id

                )  else null

            )

        }

    }

    override suspend fun createSpecifications(

        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items: List<ElementSpecification>?
    ) {

        val itemsList = mutableListOf<Items>()

        items?.forEach { group ->
            group.product.forEachIndexed { index, product ->
                // Создаем объект Items для каждого элемента
                val item = Items(
                    product_id = product.id, // Предполагаем, что ProductResponseModel имеет поле id
                    count = group.count.getOrNull(index)?.toFloatOrNull(),
                    block = group.block,
                    spectext = group.spectext.getOrNull(index)?:"",
                    price_item = group.price_item.getOrNull(index)?.toFloatOrNull(),
                    price_id = null, // Укажите, откуда брать price_id, если это поле есть
                    nds = group.nds.getOrNull(index)?.toIntOrNull()
                )
                itemsList.add(item)
            }
        }

        specificationsClient.init(getToken())

        specificationsClient.createSpecification( text = text, valuta_id = valuta_id,

            local_store_id = local_store_id, price = price, status = status,

            items = itemsList )

    }

    override suspend fun deleteSpecifications(ui: String) {

        specificationsClient.init(getToken())

        specificationsClient.deleteSpecification(ui)

    }

    override suspend fun updateSpecifications(
        ui: String,
        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items: List<ElementSpecification>?
    ) {

        val itemsList = mutableListOf<Items>()

        items?.forEach { group ->
            group.product.forEachIndexed { index, product ->
                // Создаем объект Items для каждого элемента
                val item = Items(
                    product_id = product.id, // Предполагаем, что ProductResponseModel имеет поле id
                    count = group.count.getOrNull(index)?.toFloatOrNull(),
                    block = group.block,
                    spectext = group.spectext.getOrNull(index)?:"",
                    price_item = group.price_item.getOrNull(index)?.toFloatOrNull(),
                    price_id = null, // Укажите, откуда брать price_id, если это поле есть
                    nds = group.nds.getOrNull(index)?.toIntOrNull()
                )
                itemsList.add(item)
            }
        }

        specificationsClient.init(getToken())

        specificationsClient.updateSpecification( ui = ui, text = text, valuta_id = valuta_id,

            local_store_id = local_store_id, price = price, status = status,

            items = itemsList )


    }

}
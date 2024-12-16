package datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.category_network.CategoryClient
import com.project.network.characterisrics_products_network.CharacteristicsClient
import com.project.network.system_category_network.SystemCategoryClient
import com.project.network.units_measurement_network.UnitsMeasurementClient
import domain.repository.GoodsAndServicesClientApi
import model.CategoryGoodsServicesModel
import model.CategoryLangGoodsServicesModel
import model.CategoryModel
import model.CharacteristicModel
import model.ConnectionModel
import model.DivisionModel
import model.EdizModel
import model.LangModel
import model.LangModelProduct
import model.ParameterModel
import model.ParametrDetailsModel
import model.ParametrModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel
import product_network.ProductApiClient

class GoodsAndServicesClientImpl(

    val sharedPrefsApi: SharedPrefsApi,

    val productsClient: ProductApiClient,

    val systemCategoryClient: SystemCategoryClient,

    val categoryClient: CategoryClient,

    val unitsMeasurementClient: UnitsMeasurementClient,

    val characteristicsClient: CharacteristicsClient

): GoodsAndServicesClientApi {

    override suspend fun getToken(): String {

        return sharedPrefsApi.getToken()?:""

    }

    override suspend fun getGoodsAndServices(): List<ProductGoodsServicesModel> {

        return productsClient.getProductNames().map {

            ProductGoodsServicesModel(

                id = it.id,
                name = it.name,
                video_youtube = it.video_youtube,
                category_id = it.category_id,
                ediz_id = it.ediz_id,
                ediz = if (it.ediz != null) EdizModel(

                    id = it.ediz!!.id,
                    company_id = it.ediz!!.company_id,
                    name = it.ediz!!.name,
                    ui = it.ediz!!.ui,
                    created_at = it.ediz!!.created_at,
                    updated_at = it.ediz!!.updated_at

                ) else null,
                image = it.image,
                is_product = it.is_product,
                is_sale = it.is_sale,
                min_count_store = it.min_count_store,
                is_only_industry = it.is_only_industry,
                is_view_sale = it.is_view_sale,
                is_order = it.is_order,
                is_store = it.is_store,
                is_store_view = it.is_store_view,
                is_test = it.is_test,
                is_arenda = it.is_arenda,
                is_zakaz = it.is_zakaz,
                is_ves = it.is_ves,
                ui = it.ui,
                is_serial_nomer = it.is_serial_nomer,
                is_date_fabrica = it.is_date_fabrica,
                is_markirovka = it.is_markirovka,
                is_bu = it.is_bu,
                is_ob_zvonok = it.is_ob_zvonok,
                metka_system = it.metka_system,
                sku = it.sku,
                text_image = it.text_image,
                creater = it.creater,
                nomer_creater = it.nomer_creater,
                postavka = it.postavka,
                url = it.url,
                price = it.price,
                system_category_id = it.system_category_id,
                category = if (it.category != null) CategoryModel(

                    id = it.category!!.id,
                    name = it.category!!.name,
                    creater_id = it.category!!.creater_id,
                    company_id = it.category!!.company_id,
                    created_at = it.category!!.created_at,
                    updated_at = it.category!!.updated_at

                ) else null,

                connections = it.connections?.map {

                    ConnectionModel( id = it.id )

                },

                pays = it.pays,

                prices = it.prices,

                tags = it.tags,

                components = it.components,

                completes = it.completes,

                divisions = it.divisions.map {

                    DivisionModel(

                        id = it.id,
                        company_id = it.company_id,
                        name = it.name,
                        ui = it.ui,
                        created_at = it.created_at,
                        updated_at = it.updated_at,
                        local_id = it.local_id,
                        store_id = it.store_id,
                        laravel_through_key = it.laravel_through_key

                    )

                },

                videos = it.videos,

                parametrs = it.parametrs.map{

                    ParametrModel(

                        id = it.id,
                        parametrs_id = it.parametrs_id,
                        product_id = it.product_id,
                        created_at = it.created_at,
                        updated_at = it.updated_at,
                        name = it.name,
                        parametr = if ( it.parametr != null ) ParametrDetailsModel(

                            id = it.parametr!!.id,
                            unit = it.parametr!!.unit,
                            name = it.parametr!!.name,
                            created_at = it.parametr!!.created_at,
                            updated_at = it.parametr!!.updated_at,
                            unit_id = it.parametr!!.unit_id,
                            langs = it.parametr!!.langs?.map {

                                LangModelProduct(

                                    id = it.id,
                                    name = it.name,
                                    parametrs_id = it.parametrs_id,
                                    created_at = it.created_at,
                                    updated_at = it.updated_at,
                                    lang_id = it.lang_id

                                )

                            }
                        ) else null

                    )

                },

                images = it.images,

                files = it.files
            )
        }
    }

    override suspend fun getSystemCategory(): List<SystemCategoryGoodsServicesModel> {

        systemCategoryClient.init(getToken())

        return systemCategoryClient.getSystemCategory().map {

        SystemCategoryGoodsServicesModel(

            id = it.id,
            name = it.name,
            ui = it.ui,
            created_at = it.created_at,
            updated_at = it.updated_at,
            parametrs = it.parametrs.map {

                ParameterModel(

                    id = it.id,
                    name = it.name,
                    created_at = it.created_at,
                    updated_at = it.updated_at,
                    laravel_through_key = it.laravel_through_key

                )

            }

        )

        }

    }

    override suspend fun getCategory(): List<CategoryGoodsServicesModel> {

        categoryClient.init(getToken())

        return categoryClient.getCategories().map {

            CategoryGoodsServicesModel(

             id = it.id,
                name  = it.name,
                creater_id = it.creater_id,
                company_id = it.company_id,
                created_at = it.created_at,
                updated_at = it.updated_at,
                url = it.url,
                ui = it.ui,
                category_langs = it.category_langs?.map {

                    CategoryLangGoodsServicesModel(

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

    override suspend fun getUnitsMeasurement(): List<UnitGoodsAndServicesModel> {

        unitsMeasurementClient.init(getToken())

        return unitsMeasurementClient.getUnits().map {

            UnitGoodsAndServicesModel(

                id = it.id,
                name = it.name,
                created_at = it.created_at,
                updated_at = it.updated_at,
                company_id = it.company_id,
                ui = it.ui

            )

        }

    }

    override suspend fun getCharacteristics(): List<CharacteristicModel> {

        characteristicsClient.init(getToken())

        return characteristicsClient.getCharacteristics().map {

            CharacteristicModel(

                id = it.id,
                name = it.name,
                created_at = it.created_at,
                updated_at = it.updated_at,
                langs = it.langs?.map {

                    LangModel(

                        id = it.id,
                        name = it.name,
                        parametrs_id = it.parametrs_id,
                        lang_id = it.lang_id,
                        created_at = it.created_at,
                        updated_at = it.updated_at

                    )

                }

            )

        }

    }

    override suspend fun createGoodOrService(
        name: String,
        video_youtube: String,
        ediz_id: Int?,
        category_id: Int?,
        is_product: Int?,
        is_sale: Int?,
        //min_count_store: 0 (int минимальный отстаток)
        //is_only_industry: 0/1 (только производство)
        system_category_id: Int?,
        is_view_sale: Int?,
        is_order: Int?,
        is_store: Int?,
        is_store_view: Int?,
        is_test: Int?,
        is_arenda: Int?,
        is_zakaz: Int?,
        is_ves: Int?,
        is_serial_nomer: Int?,
        is_date_fabrica: Int?,
        is_markirovka: Int?,
        is_bu: Int,
        //is_ob_zvonok: 0/1 (обратный звонок по товару)
        //metka_system: '' (Системная метка)
        sku: String,
        text_image: String,
        creater: String,
        nomer_creater: String,
        postavka: String,
        //url: '' (slug для ссылки на английском )
        price: Float?,
        tags: List<String>, //(пока что пустой массив отправлять)
        variantes: List<String>, //(пока что пустой массив отправлять)
        divisions: String, //(пока что пустой строкой отправлять)
        image_upload: String?

    ) {

     productsClient.createGoodOrService(name, video_youtube, ediz_id, category_id, is_product,

         is_sale, system_category_id, is_view_sale, is_order, is_store, is_store_view,

         is_test, is_arenda,is_zakaz, is_ves, is_serial_nomer,is_date_fabrica, is_markirovka,

         is_bu, sku, text_image, creater, nomer_creater, postavka, price, tags, variantes,

         divisions, image_upload )

    }

    override suspend fun deleteGoodOrService(id: Int) {

        productsClient.deleteGoodOrService( id )

    }

    override suspend fun updateGoodOrService(
        id: Int,
        name: String,
        video_youtube: String,
        ediz_id: Int?,
        category_id: Int?,
        is_product: Int?,
        is_sale: Int?,
        system_category_id: Int?,
        is_view_sale: Int?,
        is_order: Int?,
        is_store: Int?,
        is_store_view: Int?,
        is_test: Int?,
        is_arenda: Int?,
        is_zakaz: Int?,
        is_ves: Int?,
        is_serial_nomer: Int?,
        is_date_fabrica: Int?,
        is_markirovka: Int?,
        is_bu: Int,
        sku: String,
        text_image: String,
        creater: String,
        nomer_creater: String,
        postavka: String,
        price: Float?,
        tags: List<String>,
        variantes: List<String>,
        divisions: String,
        image_upload: String?
    ) {

        productsClient.updateGoodOrService( id, name, video_youtube, ediz_id, category_id,

            is_product, is_sale, system_category_id, is_view_sale, is_order, is_store,

            is_store_view, is_test, is_arenda,is_zakaz, is_ves, is_serial_nomer,is_date_fabrica,

            is_markirovka, is_bu, sku, text_image, creater, nomer_creater, postavka, price, tags,

            variantes, divisions, image_upload )

    }

    override suspend fun getSpecificGoodOrService( ui: String ): ProductGoodsServicesModel {

        val specificProduct = productsClient.getSpecificProduct( ui )

        return ProductGoodsServicesModel (

            id = specificProduct.id,
            name = specificProduct.name,
            video_youtube = specificProduct.video_youtube,
            category_id = specificProduct.category_id,
            ediz_id = specificProduct.ediz_id,
            ediz = if (specificProduct.ediz != null) EdizModel(

                id = specificProduct.ediz!!.id,
                company_id = specificProduct.ediz!!.company_id,
                name = specificProduct.ediz!!.name,
                ui = specificProduct.ediz!!.ui,
                created_at = specificProduct.ediz!!.created_at,
                updated_at = specificProduct.ediz!!.updated_at

            ) else null,
            image = specificProduct.image,
            is_product = specificProduct.is_product,
            is_sale = specificProduct.is_sale,
            min_count_store = specificProduct.min_count_store,
            is_only_industry = specificProduct.is_only_industry,
            is_view_sale = specificProduct.is_view_sale,
            is_order = specificProduct.is_order,
            is_store = specificProduct.is_store,
            is_store_view = specificProduct.is_store_view,
            is_test = specificProduct.is_test,
            is_arenda = specificProduct.is_arenda,
            is_zakaz = specificProduct.is_zakaz,
            is_ves = specificProduct.is_ves,
            ui = specificProduct.ui,
            is_serial_nomer = specificProduct.is_serial_nomer,
            is_date_fabrica = specificProduct.is_date_fabrica,
            is_markirovka = specificProduct.is_markirovka,
            is_bu = specificProduct.is_bu,
            is_ob_zvonok = specificProduct.is_ob_zvonok,
            metka_system = specificProduct.metka_system,
            sku = specificProduct.sku,
            text_image = specificProduct.text_image,
            creater = specificProduct.creater,
            nomer_creater = specificProduct.nomer_creater,
            postavka = specificProduct.postavka,
            url = specificProduct.url,
            price = specificProduct.price,
            system_category_id = specificProduct.system_category_id,
            category = if (specificProduct.category != null) CategoryModel(

                id = specificProduct.category!!.id,
                name = specificProduct.category!!.name,
                creater_id = specificProduct.category!!.creater_id,
                company_id = specificProduct.category!!.company_id,
                created_at = specificProduct.category!!.created_at,
                updated_at = specificProduct.category!!.updated_at

            ) else null,

            connections = specificProduct.connections?.map {

                ConnectionModel( id = it.id )

            },

            pays = specificProduct.pays,

            prices = specificProduct.prices,

            tags = specificProduct.tags,

            components = specificProduct.components,

            completes = specificProduct.completes,

            divisions = specificProduct.divisions.map {

            DivisionModel(

                id = it.id,
                company_id = it.company_id,
                name = it.name,
                ui = it.ui,
                created_at = it.created_at,
                updated_at = it.updated_at,
                local_id = it.local_id,
                store_id = it.store_id,
                laravel_through_key = it.laravel_through_key

            )

            },

            videos = specificProduct.videos,

            parametrs = specificProduct.parametrs.map{

                ParametrModel(

                    id = it.id,
                    parametrs_id = it.parametrs_id,
                    product_id = it.product_id,
                    created_at = it.created_at,
                    updated_at = it.updated_at,
                    name = it.name,
                    parametr = if ( it.parametr != null ) ParametrDetailsModel(

                        id = it.parametr!!.id,
                        unit = it.parametr!!.unit,
                        name = it.parametr!!.name,
                        created_at = it.parametr!!.created_at,
                        updated_at = it.parametr!!.updated_at,
                        unit_id = it.parametr!!.unit_id,
                        langs = it.parametr!!.langs?.map {

                            LangModelProduct(

                                id = it.id,
                                name = it.name,
                                parametrs_id = it.parametrs_id,
                                created_at = it.created_at,
                                updated_at = it.updated_at,
                                lang_id = it.lang_id

                            )

                        }

                    ) else null

                )

            },

            images = specificProduct.images,

            files = specificProduct.files

        )

    }

    override suspend fun createCharacteristic( name: String, parametr_id: Int, product_id: Int ) {

        characteristicsClient.init(getToken())

        characteristicsClient.createCharacteristic( name, parametr_id, product_id )

    }

}
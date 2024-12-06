package datasource

import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.category_network.CategoryClient
import com.project.network.system_category_network.SystemCategoryClient
import com.project.network.units_measurement_network.UnitsMeasurementClient
import domain.repository.GoodsAndServicesClientApi
import model.CategoryGoodsServicesModel
import model.CategoryLangGoodsServicesModel
import model.CategoryModel
import model.EdizModel
import model.ParameterModel
import model.ProductGoodsServicesModel
import model.SystemCategoryGoodsServicesModel
import model.UnitGoodsAndServicesModel
import product_network.ProductApiClient

class GoodsAndServicesClientImpl(

    val sharedPrefsApi: SharedPrefsApi,

    val productsClient: ProductApiClient,

    val systemCategoryClient: SystemCategoryClient,

    val categoryClient: CategoryClient,

    val unitsMeasurementClient: UnitsMeasurementClient

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
                ediz = if (it.ediz != null) EdizModel(

                    id = it.ediz!!.id,
                    company_id = it.ediz!!.company_id,
                    name = it.ediz!!.name,
                    ui = it.ediz!!.ui,
                    created_at = it.ediz!!.created_at,
                    updated_at = it.ediz!!.updated_at

                ) else null,
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

                ) else null
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

}
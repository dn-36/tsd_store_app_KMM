package com.datasousrce

import com.domain.repository.ProductCategoriesClientApi
import com.model.CategoriesResponseModel
import com.model.CategoriesLangModel
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.category_network.CategoryClient

class ProductCategoriesClientImpl(

    val categoryClient: CategoryClient,

    val sharedPrefsApi: SharedPrefsApi

): ProductCategoriesClientApi {

    override suspend fun getToken(): String {

        return sharedPrefsApi.getToken()?:""

    }

    override suspend fun getCategories(): List<CategoriesResponseModel> {

        categoryClient.init(getToken())

        return categoryClient.getCategories().map {

            CategoriesResponseModel(

                id = it.id,
                name = it.name,
                creater_id = it.creater_id,
                created_at = it.created_at,
                company_id = it.company_id,
                updated_at = it.updated_at,
                url = it.url,
                ui = it.ui,
                category_langs = it.category_langs?.map {

                    CategoriesLangModel(

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
package com.project.`menu-crm-api`.categories.datasource

import com.project.`menu-crm-api`.categories.domain.repository.CategoryClientApi
import com.project.`menu-crm-api`.categories.model.CategoryLangModel
import com.project.`menu-crm-api`.categories.model.CategoryResponseModel
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.category_network.CategoryClient

class CategoryClientImpl (

    val shareredPrefsApi: SharedPrefsApi,

    val categoryClient: CategoryClient

): CategoryClientApi {

    override suspend fun getToken(): String {

        return shareredPrefsApi.getToken()?:""

    }

    override suspend fun getCategories(): List<CategoryResponseModel> {

        categoryClient.init(getToken())

        return categoryClient.getCategories().map {

        CategoryResponseModel(

            id = it.id,
            name = it.name,
            creater_id = it.creater_id,
            created_at = it.created_at,
            company_id = it.company_id,
            updated_at = it.updated_at,
            url = it.url,
            ui = it.ui,
            category_langs = it.category_langs.map {

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

    override suspend fun createCategory(name: String) {

        categoryClient.init(getToken())

        categoryClient.createCategory(name)

    }

    override suspend fun deleteCategory(id: Int) {

        categoryClient.init(getToken())

        categoryClient.deleteCategory(id)

    }

    override suspend fun updateCategory(name: String, id: Int) {

        categoryClient.init(getToken())

        categoryClient.updateCategory( name, id )

    }

}
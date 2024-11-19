package com.project.`menu-crm-impl`

import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.`menu-crm-api`.categories.datasource.CategoryClientImpl
import com.project.`menu-crm-api`.categories.domain.repository.CategoryClientApi
import com.project.`menu-crm-api`.categories.domain.usecases.GetCategoriesUseCase
import com.project.menu.CategoriesViewModel
import com.project.menu_crm_api.categories.domain.usecases.CreateCategoryUseCase
import com.project.menu_crm_api.categories.domain.usecases.DeleteCategoryUseCase
import com.project.menu_crm_api.categories.domain.usecases.UpdateCategoryUseCase
import com.project.network.category_network.CategoryClient
import org.koin.dsl.module

val menuCrmModule = module {

    factory { MenuCrmScreenImpl() as MenuCrmScreenApi }



    factory { CategoryClientImpl(get(), get()) as CategoryClientApi }

    factory { CategoryClientImpl(get(), get()) as CategoryClientApi }

    factory { GetCategoriesUseCase(get()) }

    factory { CreateCategoryUseCase(get()) }

    factory { UpdateCategoryUseCase(get()) }

    factory { DeleteCategoryUseCase(get()) }

    factory { CategoriesViewModel(get(), get(), get(), get())}

    factory { CategoryClient()}
}